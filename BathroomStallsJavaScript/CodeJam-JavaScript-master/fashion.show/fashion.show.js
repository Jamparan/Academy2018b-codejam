const fs = require("fs");
const lines = fs.readFileSync("fashion.show/inputs/D-small-practice.in", "utf8").split("\n");
const outputFilePath = 'fashion.show/output.txt'

const numberOfCases = parseInt(lines.shift());
const figuras = ["o", "x", "+", "."];
let grid;
let legal = false;
let gridSize;
let fashionPoints;
let modifications;
let results
let modelsInGrid;

fashionShow();

function fashionShow() {
  cleanOutputFile();
  for (let j = 0; j < numberOfCases; j++) {
    grid = new Array();
    const line = lines.shift().split(' ')
    fashionPoints = 0;
    modifications = 0;
    results = new Array();
    gridSize = parseInt(line[0])
    modelsInGrid = parseInt(line[1]);

    for (let i = 0; i < gridSize; i++) {
      grid[i] = new Array(gridSize).fill('.')
    }
    for (let i = 0; i < modelsInGrid; i++) {
      const line = lines.shift().split(' ');
      const figure = line.shift();
      const x = line.shift();
      const y = line.shift();
      grid[x - 1][y - 1] = figure;
    }
    for (let i = (gridSize - 1); i >= 0; i--) {
      for (let k = (gridSize - 1); k >= 0; k--) {
        const figuraActual = grid[i][k];
        for (let fig of figuras) {
          if (validateFigure(i, k, fig)) {
            if (fig === "o") {
              fashionPoints += 2;
            } else if (fig === "x" || fig === "+") {
              fashionPoints++;
            }

            if (figuraActual !== fig) {
              results.push(`${fig} ${(i + 1)} ${(k + 1)} \n`);
              modifications++;
            } else {
              grid[i][k] = figuraActual;
            }
            break;
          }
        }
      }
    } // fin del for validar

    fs.appendFile(outputFilePath, `Case # ${(j + 1)}: ${fashionPoints} ${modifications}\n`,  (err) => {
      if (err) throw err;
    });

    for (let result of results) {
      fs.appendFile(outputFilePath, result, (err) => {
        if (err) throw err;
      });
    }
  }
}

function cleanOutputFile() {
  fs.writeFile(outputFilePath, '', (err) => {
    if (err) throw err;
  });
}

function validatePlus(PosX, PosY) {
  let rowCount = 0;
  let columnCount = 0;

  for (let i = 0; i < gridSize; i++) {
    if (grid[i][PosY] !== "+" && grid[i][PosY] !== ".") {
      rowCount++;
    }
    if (grid[PosX][i] !== "+" && grid[PosX][i] !== ".") {
      columnCount++;
    }
  }
  if (columnCount > 1 || rowCount > 1) {
    return false;
  }
  return true;
}

function validateX(PosX, PosY) {
  let rightCount = 0;
  let leftCount = 0;

  if (grid[PosX][PosY] !== "x" && grid[PosX][PosY] !== ".") {
    rightCount++;
    leftCount++;
  }

  for (let i = 1; i < gridSize; i++) {
    let xUp = PosX + i;
    let yUp = PosY + i;
    let xDown = PosX - i;
    let yDown = PosY - i;

    //RightCount
    if (xUp < gridSize && yUp < gridSize) {
      if (grid[xUp][yUp] !== "x" && grid[xUp][yUp] !== ".") {
        rightCount++;
      }
    }
    if (xDown >= 0 && yDown >= 0) {
      if (grid[xDown][yDown] !== "x" && grid[xDown][yDown] !== ".") {
        rightCount++;
      }
    }

    //LeftCount
    if (xUp < gridSize && yDown >= 0) {
      if (grid[xUp][yDown] !== "x" && grid[xUp][yDown] !== ".") {
        leftCount++;
      }
    }
    if (xDown >= 0 && yUp < gridSize) {
      if (grid[xDown][yUp] !== "x" && !grid[xDown][yUp] !== ".") {
        leftCount++;
      }
    }
  }

  if (leftCount > 1 || rightCount > 1) {
    return false;
  }

  return true;
}


function validateFigure(X, Y, fig) {
  legal = false;

  grid[X][Y] = fig;

  if (validatePlus(X, Y)) {
    if (validateX(X, Y)) {
      legal = true;
    }
  }
  return legal;
}