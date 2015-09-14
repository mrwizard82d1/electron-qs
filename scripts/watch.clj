(require '[cljs.build.api :as b])

(b/watch "src"
  {:main 'electron-qs.core
   :output-to "out/electron_qs.js"
   :output-dir "out"})
