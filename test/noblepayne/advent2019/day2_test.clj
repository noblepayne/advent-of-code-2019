(ns noblepayne.advent2019.day2-test
  (:require [clojure.test :refer :all]
            [clojure.java.io :as io]
            [noblepayne.advent2019.day2 :refer :all]))

(def testdata-1
  (parse-input "1,9,10,3,2,3,11,0,99,30,40,50"))

(def testdata-2
  (parse-input "1,0,0,0,99"))

(def testdata-3
  (parse-input "2,3,0,3,99"))

(def testdata-4
  (parse-input "2,4,4,5,99,0"))

(def testdata-5
  (parse-input "1,1,1,4,99,5,6,0,99"))

(def realdata (-> "day2p1.txt" io/resource slurp parse-input))

;;;;;;;;;; part 1 ;;;;;;;;;;

(deftest provided-test-1
  (testing "p1 test 1"
    (is (= 3500
           (run testdata-1)))))


(deftest provided-test-2
  (testing "p1 test 2"
    (is (= 2
           (run testdata-2)))))

(deftest provided-test-3
  (testing "p1 test 3"
    (is (= 2
           (run testdata-3)))))

(deftest provided-test-4
  (testing "p1 test 4"
    (is (= 2
           (run testdata-4)))))

(deftest provided-test-5
  (testing "p1 test 5"
    (is (= 30
           (run testdata-5)))))

(deftest real-test-1
  (testing "p1"
    (is (= 7594646
           (solve1 realdata)))))

;;;;;;;;;; part 2 ;;;;;;;;;;

(deftest real-test-2
  (testing "p2"
    (is (= 3376
           (solve2 realdata)))))
