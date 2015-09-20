(ns electron-qs.core
  (:require [cljs.nodejs :as nodejs]))

(nodejs/enable-util-print!)

(def process (js/require "process"))

;; The module controlling the application life cycle.
(def app (js/require "app"))

;; A browser window (class).
(def BrowserWindow (js/require "browser-window"))

;; Report crashes.
(.start (js/require "crash-reporter"))

;; Retain a reference to the main window so JavaScript does not destroy the instance.
(def main-window (atom nil))

;; Quit when the user closes all windows.
(.on app "window-all-closed"
     ;; On OS X, it is common for applications and their menu bar to remain active until the user explicitly closes
     ;; the application using Cmd-Q.
     (fn []
       (when-not (= "darwin" (.platform process))
         (.quit app))))

;; When the electron has finished initialization
(defn do-when-ready []
  ;; Create the browser window.
    (swap! main-window BrowserWindow. (clj->js {:width 800 :height 600}))
  )
(.on app "ready" do-when-ready)

(defn -main [& args]
  (println "Hello, Electron World!"))

(set! *main-cli-fn* -main)

