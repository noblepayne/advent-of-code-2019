(ns noblepayne.advent2019.day1-test
  (:require [clojure.test :refer :all]
            [noblepayne.advent2019.day1 :refer :all]
            [clojure.java.io :as io]))

(def realdata (clean-data (slurp (io/resource "day1p1.txt"))))

(deftest provided-test-1
  (testing "p1 test 1"
    (is (= (calculate-fuel 12) 2))))
           
(deftest provided-test-2
  (testing "p1 test 2"
    (is (= (calculate-fuel 14) 2))))
           
(deftest provided-test-3
  (testing "p1 test 3"
    (is (= (calculate-fuel 1969) 654))))

(deftest provided-test-4
  (testing "p1 test 4"
    (is (= (calculate-fuel 100756) 33583))))

(deftest realtest-1
  (testing "p1"
    (is (= 3346639 (solve realdata)))))

;;;;;;;;;;;;;; part 2 ;;;;;;;;;;;;;;;;;;;;

(deftest provided-test-5
  (testing "p2 test 1"
    (is (= (calculate-fuel2 14) 2))))

(deftest provided-test-6
  (testing "p2 test 2"
    (is (= (calculate-fuel2 1969) 966))))

(deftest provided-test-7
  (testing "p2 test 3"
    (is (= (calculate-fuel2 100756) 50346))))

(deftest realtest-2
  (testing "p2"
    (is (= 5017110 (solve2 realdata)))))
