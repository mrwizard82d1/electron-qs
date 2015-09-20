(ns electron-qs.core
  (:require [cljs.nodejs :as nodejs]))

(nodejs/enable-util-print!)

(defn -main [& args]
  (println "Hello, Electron World!"))

(set! *main-cli-fn* -main)

