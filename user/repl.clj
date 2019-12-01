(ns repl
  "Entry point for starting rebel-readline REPL.
  Inspired by rebel-readline.main.
  Usage: [LEIN_FAST_TRAMPOLINE=1] lein trampoline run -m repl.rebel [init-ns]"
  (:require [clojure.repl :as clj-repl]
            [rebel-readline.core :as rebel-core]
            [clojure.tools.nrepl.server :refer [start-server]]
            [rebel-readline.clojure.main :as rebel-clj-main]))

(defn -handle-sigint-form
  []
  `(let [thread# (Thread/currentThread)]
     (clj-repl/set-break-handler! (fn [_signal#] (.stop thread#)))))

(defn -main
  [& [init-ns]]
  (def server)
  (rebel-core/ensure-terminal
    (rebel-clj-main/repl*
      {:init (fn []
               ; HACK: rebel-readline doesn't have a convenient way to change init ns (it's always `user`).
               ; See https://github.com/bhauman/rebel-readline/issues/157.
               (start-server :port 9999 :middleware ["cider.nrepl/cider-middleware"])
               (doto
                 (clojure.java.io/file ".nrepl-port")
                 .deleteOnExit
                 (spit 9999))
               (when (some? init-ns)
                 (let [init-ns (symbol init-ns)]
                   (require init-ns)
                   (in-ns init-ns))))

       :eval (fn [form]
               ; HACK: allows Ctrl+C to interrupt long running tasks.
               ; See https://github.com/bhauman/rebel-readline/issues/180#issuecomment-429057767.
               (eval `(do ~(-handle-sigint-form) ~form)))})))

