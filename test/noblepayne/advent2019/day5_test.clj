(ns noblepayne.advent2019.day5-test
  (:require [clojure.test :refer :all]
            [clojure.java.io :as io]
            [noblepayne.advent2019.day5 :refer :all]))

(def realdata (-> "day5p1.txt" io/resource slurp parse-input))

;;;;;;;;;; part 1 ;;;;;;;;;;

(deftest realtest-1
  (testing "p1"
    (is (= 7988899 (solve1 realdata)))))

;;;;;;;;;; part 2 ;;;;;;;;;;

(deftest realtest-2
  (testing "p2"
    (is (= 13758663 (solve2 realdata)))))
