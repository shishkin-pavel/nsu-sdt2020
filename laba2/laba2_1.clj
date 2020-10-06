(ns laba2-1.core
  (:gen-class))



(defn computation [fun x0 x1]
  (+ (fun x0) (fun x1)))

(defn addition_division [fun x0 x1 dx]
  (loop [c0 x0 c1 (+ x0 dx) buffer 0] 
    (if (< c1 x1) 
      (recur (+ c0 dx) (+ c1 dx) (+ (computation fun c0 c1) buffer))
      (* (+ (computation fun c0 c1) buffer) (/ dx 2))
        )))



(defn function [fun x]
  memoize (fun x))

(defn computation2 [fun x0 x1]
  (+ (function fun x0) (function fun x1)))

(defn addition_division2 [fun x0 x1 dx]
  (loop [c0 x0 c1 (+ x0 dx) buffer 0]
    (if (< c1 x1)
      (recur (+ c0 dx) (+ c1 dx) (+ (computation2 fun c0 c1) buffer))
      (* (+ (computation2 fun c0 c1) buffer) (/ dx 2)))))


(do
  (time (addition_division (fn [x] (* x x)) 0 1000 0.005))
  (time (addition_division2 (fn [x] (* x x)) 0 1000 0.005))
  
  (time (addition_division (fn [x] (* x x)) 0 10000 0.0005))
(time (addition_division2 (fn [x] (* x x)) 0 10000 0.0005))
  )
