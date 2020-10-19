(ns laba2-2.core
  (:gen-class))


(defn trapec [fun dx]
  (map first (iterate
       (fn [[sum x]] [(+ sum (* (* (+ (fun (- x dx)) (fun x)) 0.5) dx)) (+ x dx)]) [0.0 dx])))

(defn integral [fun x dx]
  (nth (trapec fun dx) (- (/ x dx) 1)))


(def f #(* % %))
(def dx 1)
(defn -main []
  (print "num:100 -")
  (time (integral f 100 dx))
  (print "num:100 -")
  (time (integral f 100 dx))
  (print "num:110 -")
  (time (integral f 110 dx))
  (print "num:120 -")
  (time (integral f 120 dx))
  (print "num:130 -")
  (time (integral f 130 dx))
  (print "num:140 -")
  (time (integral f 140 dx)))
(-main)