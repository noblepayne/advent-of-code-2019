(ns noblepayne.advent2019.day4-test
  (:require [clojure.test :refer :all]
            [clojure.java.io :as io]
            [noblepayne.advent2019.day4 :refer :all]))

(def realdata (-> "day4p1.txt" io/resource slurp parse-input))

;;;;;;;;;; part 1 ;;;;;;;;;;

(deftest provided-test-1
  (testing "p1 test 1"
    (is (passes-tests-p1? 111111))))

(deftest provided-test-2
  (testing "p1 test 2"
    (is (not (passes-tests-p1? 223450))))) 

(deftest provided-test-3
  (testing "p1 test 3"
    (is (not (passes-tests-p1? 123789))))) 

(deftest realtest-1
  (testing "p1"
    (let [[lb ub] realdata]
      (is (= 1955 (solve1 lb ub))))))

;;;;;;;;;; part 2 ;;;;;;;;;;

(deftest provided-test-4
  (testing "p2 test 1"
    (is (passes-tests-p2? 112233))))

(deftest provided-test-5
  (testing "p2 test 2"
    (is (not (passes-tests-p2? 123444))))) 

(deftest provided-test-6
  (testing "p2 test 3"
    (is (passes-tests-p2? 111122)))) 

(deftest realtest-2
  (testing "p2"
    (let [[lb ub] realdata]
      (is (= 1319 (solve2 lb ub))))))
