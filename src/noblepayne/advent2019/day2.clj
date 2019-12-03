(ns noblepayne.advent2019.day2
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(defn parse-input [input]
  (mapv #(Integer/parseInt %)
    (-> input
        str/trim
        (str/split #","))))

(defn init-state [data]
  {:program data
   :pos 0})

(defn run-opcode [operation {:keys [:pos :program] :as state}]
  (let [op1 (->> pos inc (get program) (get program)) 
        op2 (->> pos (+ 2) (get program) (get program))
        output-pos (->> pos (+ 3) (get program)) 
        res (operation op1 op2)
        newpos (+ pos 4)
        newprog (assoc program output-pos res)]
    {:pos newpos :program newprog}))

(def run-opcode-1 (partial run-opcode +))
(def run-opcode-2 (partial run-opcode *))

(defn run [data]
  (loop [{:keys [:pos :program] :as state} (init-state data)]
    (let [opcode (get program pos)]
      (case opcode
        1 (recur (run-opcode-1 state))
        2 (recur (run-opcode-2 state))
        99 (-> state :program first)))))

(defn prepare-data [data noun verb]
  (assoc data 1 noun 2 verb))

(defn solve1 [data]
  (run (prepare-data data 12 2)))

;;;;;;;;;;;;;;;;;;;;;;;;;;; part 2 ;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn try-input [data noun verb]
  (let [data (prepare-data data noun verb)
        output (run data)]
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
