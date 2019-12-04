(ns noblepayne.advent2019.day3
  (:require [clojure.java.io :as io]
            [clojure.string :as str]
            [clojure.set :as set]))

(def dir-translate
  {\U [0 1]
   \D [0 -1]
   \L [-1 0]
   \R [1 0]})

(defn parse-input [raw-data]
  (->> (str/split-lines raw-data)
       (map #(str/split % #","))
       (map (fn [line]
              (map (fn [[dir & dist]]
                     {:dir (dir-translate dir)
                      :dist (Integer/parseInt (apply str dist))})
                   line)))))

(defn scale-dir [[x y] scale]
  [(* x scale) (* y scale)]) 

(defn segment-locations [offset {:keys [:dir :dist]}]
  (for [i (range 1 (inc dist))]
    (mapv +
          offset
          (scale-dir dir i))))

(defn line-locations [line]
  (reduce
    (fn [locations segment]
      (let [offset (peek locations)
            new-locations (segment-locations offset segment)]
        (into locations new-locations)))
    [[0 0]]
    line))

(defn line-location-set [line]
  (let [locations (line-locations line)
        locations-set (into #{} locations)]
    (disj locations-set [0 0])))

(defn line-intersections [lines]
  (apply set/intersection
         (map line-location-set lines)))

(defn manhattan-distance [[x1 y1] [x2 y2]]
  (+ (Math/abs (- y2 y1))
     (Math/abs (- x2 x1))))

(defn find-closest-intersection [lines]
  (let [intersections (line-intersections lines)
        distances (map #(manhattan-distance [0 0] %)
                       intersections)]
    (apply min distances))) 

;;;;;;;;;;;;;;;; part 2 ;;;;;;;;;;;;;;;;;;;;

(defn line-location-map [line]
  (let [locations (line-locations line)
        indexed-locations (map-indexed 
                            (comp vec reverse vector)
                            locations)]
    (into {} indexed-locations)))

(defn find-least-delay-intersection [lines]
 (let [intersections (line-intersections lines)
       linemap1  (line-location-map (first lines))
       linemap2  (line-location-map (second lines))
       distances (map #(+ (get linemap1 %)
                          (get linemap2 %))
                      intersections)]
   (apply min distances)))
