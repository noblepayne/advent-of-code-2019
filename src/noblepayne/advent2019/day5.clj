(ns noblepayne.advent2019.day5
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(defn parse-input [input]
  (mapv #(Integer/parseInt %)
    (-> input
        str/trim
        (str/split #","))))

(def data (-> "day5p1.txt" io/resource slurp parse-input))

(defn init-state [data input]
  {:program data
   :input [input]
   :output []
   :pos 0})

(defn read-opcode-with-modes [opcode]
  (let [[opdigit1 opdigit2 & modes] (reverse (map #(Integer/parseInt (str %)) (str opcode)))
        opcode (+ (* 10 (or opdigit2 0)) opdigit1)]
    {:opcode opcode
     :modes (vec modes)}))

(defn get-value [{:keys [:program :pos] :as state} modes inst-num]
  (let [mode (get modes (dec inst-num) 0)
        readpos (+ pos inst-num)]
    (case mode
      0 (->> readpos (get program) (get program))
      1 (->> readpos (get program)))))

(defn arithmetic-opcode [operation {:keys [:pos :program] :as state} modes]
  (let [op1 (get-value state modes 1)
        op2 (get-value state modes 2)
        output-pos (get program (+ pos 3))
        res (operation op1 op2)
        newpos (+ pos 4)
        newprog (assoc program output-pos res)]
    (assoc state :pos newpos :program newprog)))

(def opcode-1 (partial arithmetic-opcode +))
(def opcode-2 (partial arithmetic-opcode *))

(defn opcode-3 [{:keys [:input :pos :program] :as state} modes]
  (let [op-input (peek input)
        new-input (pop input)
        output-pos (get program (inc pos))
        newprog (assoc program output-pos op-input)
        newpos (+ pos 2)]
    (assoc state :pos newpos :program newprog :input new-input)))

(defn opcode-4 [{:keys [:output :pos :program] :as state} modes]
  (let [newpos (+ pos 2)
        op1 (get-value state modes 1)
        new-output (conj output op1)]
    (assoc state :output new-output :pos newpos))) 

(defn jump-opcode [testfn {:keys [:pos :program] :as state} modes]
  (let [op1 (get-value state modes 1)
        op2 (get-value state modes 2)
        newpos (if (testfn op1)
                 op2
                 (+ pos 3))]
    (assoc state :pos newpos)))

(def opcode-5 (partial jump-opcode (comp not zero?)))
(def opcode-6 (partial jump-opcode zero?))

(defn cmp-opcode [testfn {:keys [:pos :program] :as state} modes]
  (let [newpos (+ pos 4)
        op1 (get-value state modes 1)
        op2 (get-value state modes 2)
        op3 (get program (+ pos 3))
        output (if (testfn op1 op2) 1 0)
        newprog (assoc program op3 output)]
    (assoc state :pos newpos :program newprog)))

(def opcode-7 (partial cmp-opcode <))
(def opcode-8 (partial cmp-opcode =))

(defn opcode-99 [state modes]
  ::halt) 

(def opcodes
  {1 opcode-1
   2 opcode-2
   3 opcode-3
   4 opcode-4
   5 opcode-5
   6 opcode-6
   7 opcode-7
   8 opcode-8
   99 opcode-99})

(defn run [data input]
  (loop [{:keys [:pos :program] :as state} (init-state data input)]
    (let [{:keys [:opcode :modes]} (read-opcode-with-modes
                                    (get program pos))
          output ((opcodes opcode) state modes)]
      (if (not= ::halt output)
        (recur output)
        state))))

(defn solve1 [input]
  (peek (:output (run input 1))))

(defn solve2 [input]
  (peek (:output (run input 5))))
