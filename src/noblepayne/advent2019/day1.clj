(ns noblepayne.advent2019.day1
 (:require [clojure.java.io :as io]
           [clojure.string :as str]))

(def p1-raw-data (slurp (io/resource "day1p1.txt")))

(defn clean-data [raw-data]
  (->> (str/split-lines raw-data)
       (map #(Integer/parseInt %))))

(def p1-data (clean-data p1-raw-data))

(defn calculate-fuel [mass]
  (-> mass
      (/ 3)
      int
      (- 2)))

(defn solve [cleaned-data]
  (reduce + (map calculate-fuel cleaned-data)))

(defn calculate-fuel2
  ([mass] (calculate-fuel2 0 mass))
  ([sum mass]
   (let [f (calculate-fuel mass)]
     (if-not (pos? f)
       sum
       (recur (+ sum f) f)))))

(defn solve2 [cleaned-data]
  (reduce + (map calculate-fuel2 cleaned-data)))
