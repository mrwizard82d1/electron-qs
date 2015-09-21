(ns electron-qs.core
  (:require [cljs.nodejs :as nodejs]))

(nodejs/enable-util-print!)

(def process (js/require "process"))

(defn quit-if-not-mac-osx [app]
  "On OS X, it is common for applications and their menu bar to remain active until the user explicitly closes the application
using Cmd-Q."
  (when-not (= "darwin" (.platform process))
         (.quit app)))

;; When the electron has finished initialization
(defn do-when-ready [main-window]
  (let [BrowserWindow (js/require "browser-window")]
    (println (str "In do-when-ready/1: "  (if @main-window @main-window "nil")))
    ;; Create the browser window.
    (println "BrowserWindow before " BrowserWindow)
    (swap! main-window BrowserWindow. (clj->js {:width 800 :height 600}))
    (println "main-window " main-window)
    (println (str "file://" js/__dirname "index.html"))
    (.loadUrl main-window (str "file://" js/__dirname "index.html"))
    ;; (.on main-window "closed"
    ;;      (fn []
    ;;        ;; Deference the window object. If your app supports multiple windows, you would typically store them in an array.
    ;;        ;; In this situation, you would delete the corresponding element of the array.
    ;;        (swap! main-window identity nil)))
))

(defn -main [& args]
  (let [ ;; require the node project module
        app (js/require "app") ;; define the application
        main-window (atom nil)]
    (.start (js/require "crash-reporter"))
    (println "Before setting 'window-all-closed.'")
    ;; (.on app "window-all-closed" (partial quit-if-not-mac-osx app))
    (println "before setting 'ready'")
    (.on app "ready" (partial do-when-ready main-window))

    
    (println "Hello, Electron World!"))
  )

(set! *main-cli-fn* -main)

