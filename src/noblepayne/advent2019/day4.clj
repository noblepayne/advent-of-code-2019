(ns noblepayne.advent2019.day4
  (:require [clojure.java.io :as io]
            [clojure.string :as str]
            [clojure.set :as set]))

(defn parse-input [input]
  (map #(Integer/parseInt %)
       (str/split
         (str/trim-newline input)
         #"-")))

(defn number-of-digits [n]
  (count (str n)))

(defn six-digits? [n]
  (= 6 (number-of-digits n)))

(defn increasing? [n]
  (apply <= (map #(Integer/parseInt (str %)) (str n))))

(defn repeating-digit? [n]
  (->> (str n)
       (partition 2 1)
       (filter #(apply = %))
       seq))
       
(defn repeating-digit-only-two? [n]
  (let [parts (partition 2 1 (str n))
        part-freq (frequencies parts)]
    (seq
      (filter (fn [part]
                (and (apply = part)
                     (= 1 (part-freq part))))
              parts))))
  
(defn passes-tests-p1? [n]
  (and (six-digits? n)
       (increasing? n)
       (repeating-digit? n)))

(defn passes-tests-p2? [n]
  (and (six-digits? n)
       (increasing? n)
       (repeating-digit-only-two? n)))

(defn count-passing [testfn lb ub]
  (count
    (filter testfn
            (range lb (inc ub)))))

(defn solve1 [lb ub]
  (count-passing passes-tests-p1? lb ub))

(defn solve2 [lb ub]
  (count-passing passes-tests-p2? lb ub))


(comment
  (let [input (slurp (io/resource "day4p1.txt"))
        [lb ub] (parse-input input)]
    (println
      (solve1 lb ub))
    (println
      (solve2 lb ub))))
