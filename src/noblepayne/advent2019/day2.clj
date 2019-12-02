(ns noblepayne.advent2019.day2
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(defn parse-input []
  (mapv #(Integer/parseInt %)
    (-> "day2p1.txt"
        io/resource
        slurp
        str/trim
        (str/split #","))))

; (def input-data (parse-input))
; (def data (assoc input-data 1 12 2 2))

(defn init-state [data]
  {:program data
   :pos 0})

(defn run-opcode [operation {:keys [:pos :program] :as state}]
  (let [op1 (get program (get program (inc pos)))
        op2 (get program (get program (+ 2 pos)))
        output-pos (get program (+ 3 pos))
        res (operation op1 op2)
        newpos (+ pos 4)
        newprog (assoc program output-pos res)]
    {:pos newpos :program newprog}))

(def run-opcode-1 (partial run-opcode +))
(def run-opcode-2 (partial run-opcode *))

(defn solve1 [data]
  (loop [{:keys [:pos :program] :as state} (init-state data)]
    (let [opcode (get program pos)]
      (case opcode
        1 (recur (run-opcode-1 state))
        2 (recur (run-opcode-2 state))
        99 (-> state :program first)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;; part 2 ;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn prepare-data [data noun verb]
  (assoc data 1 noun 2 verb))

(defn try-input [data noun verb]
  (let [data (prepare-data data noun verb)
        output(solve1 data)]
    output))

(defn find-winner [winning-num data]
  (->> (for [n (range 0 100)
             v (range 0 100)]
         [n v (try-input data n v)])
       (filter (comp #{winning-num} peek))
       (take 1)
       first))

(defn solve2 [data]
  (let [[n v _] (find-winner 19690720 data)]
    (+ v (* 100 n))))
