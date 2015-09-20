var app = require('app'); // Module controlling application life cyle.
var BrowserWindow = require('browser-window'); // Creating native browser windows.

// Report crashes to the server (local node process?).
require('crash-reporter').start();

// Keep a global reference to the main window; otherwise, when JavaScript garbage collects the instance,
// it will destroy the window referred to by the instance. :) (How difficult would that be to debug?)
var mainWindow = null;

// Quit when the user closes all windows.
app.on('window-all-closed', function() {
  // On OS X, it is common for applications and their menu bar to remain active until the user explicitly
  // closes the application using Cmd-Q.
  if (process.platform != 'darwin') {
    app.quit();
  }
})

// When electron has finished initialization and is ready to create browser windows, we call the following:
app.on('ready', function() {
  // Create the browser window.
  mainWindow = new BrowserWindow({width: 800, height: 600});

  // Load the index page for the app.
  mainWindow.loadUrl('file://' + __dirname + '/index.html');

  // Open the development tools. 
  mainWindow.openDevTools();

  // When main window closed
  mainWindow.on('closed', function() {
    // Deference the window object. If you app supports multiple windows, you would typically store them
    // in an array. In this situation, you would delete the corresponding element of the array.
    mainWindow = null;
  })
})
