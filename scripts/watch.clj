(require '[cljs.build.api])

(cljs.build.api/watch "src"
                      {:main 'electron-qs.core
                       :output-to "out/electron_qs.js"
                       :output-dir "out"
                       :target :nodejs})
