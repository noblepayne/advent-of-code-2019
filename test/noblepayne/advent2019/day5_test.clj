(ns noblepayne.advent2019.day5-test
  (:require [clojure.test :refer :all]
            [clojure.java.io :as io]
            [noblepayne.advent2019.day5 :refer :all]))

(def testdata1
  (parse-input
    "3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99"))

(def realdata (-> "day5p1.txt" io/resource slurp parse-input))

;;;;;;;;;; part 1 ;;;;;;;;;;

(deftest realtest-1
  (testing "p1"
    (is (= 7988899 (solve1 realdata)))))

;;;;;;;;;; part 2 ;;;;;;;;;;

(deftest providedtest-1
  (testing "p2 test 1"
    (is (= 999 (peek (:output (run testdata1 4)))))))

(deftest providedtest-2
  (testing "p2 test 2"
    (is (= 1001 (peek (:output (run testdata1 12)))))))

(deftest providedtest-3
  (testing "p2 test 3"
    (is (= 1000 (peek (:output (run testdata1 8)))))))

(deftest realtest-2
  (testing "p2"
    (is (= 13758663 (solve2 realdata)))))
