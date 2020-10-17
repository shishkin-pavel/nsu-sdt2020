(ns test.task-2-1)

(defn trapeze-method [func xi xi+1]
  (* (/ (+ (func xi) (func xi+1)) 2) (- xi+1 xi)))

(def sum
  (memoize (fn [func dx x]
             (loop [xi 0.0 xi+1 dx sum 0.0]
               (if (> xi+1 x)
                 sum
                 (recur xi+1 (+ xi+1 dx) (+ sum (trapeze-method func xi xi+1))))))))

(defn get-rest [x dx]
  (- x (* (Math/floor (/ x dx)) dx)))

(defn my-integration [func dx]
  (fn [x]
    (+ (sum func dx (- x (get-rest x dx))) (/ (* (+ (func x) (func (- x (get-rest x dx)))) (get-rest x dx)) 2))))

(def calc (my-integration (fn [x] (* x x)) 0.3))

(defn -main [& _]
  (time (calc 1000))
  (time (calc 1001))
  (time (calc 1002)))
