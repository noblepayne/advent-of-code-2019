(ns noblepayne.advent2019.day3-test
  (:require [clojure.test :refer :all]
            [clojure.java.io :as io]
            [noblepayne.advent2019.day3 :refer :all]))

(def testdata-1
  (parse-input "R8,U5,L5,D3\nU7,R6,D4,L4"))

(def testdata-2
  (parse-input "R75,D30,R83,U83,L12,D49,R71,U7,L72\nU62,R66,U55,R34,D71,R55,D58,R83"))

(def testdata-3
  (parse-input "R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51\nU98,R91,D20,R16,D67,R40,U7,R15,U6,R7"))

(def realdata (-> "day3p1.txt" io/resource slurp parse-input))

;;;;;;;;;; part 1 ;;;;;;;;;;

(deftest provided-test-1
  (testing "p1 test 1"
    (is (= 6))))


(deftest provided-test-2
  (testing "p1 test 2"
    (is (= 159
           (find-closest-intersection testdata-2)))))

(deftest provided-test-3
  (testing "p1 test 3"
    (is (= 135
           (find-closest-intersection testdata-3)))))

(deftest real-test-1
  (testing "p1"
    (is (= 1017
           (find-closest-intersection realdata)))))

;;;;;;;;;; part 2 ;;;;;;;;;;

(deftest provided-test-4
  (testing "p2 test 1"
    (is (= 30
           (find-least-delay-intersection testdata-1)))))

(deftest provided-test-5
  (testing "p2 test 2"
    (is (= 610
           (find-least-delay-intersection testdata-2)))))

(deftest provided-test-6
  (testing "p2 test 3"
    (is (= 410
           (find-least-delay-intersection testdata-3)))))

(deftest real-test-2
  (testing "part 2"
   (is (= 11432
          (find-least-delay-intersection realdata)))))
