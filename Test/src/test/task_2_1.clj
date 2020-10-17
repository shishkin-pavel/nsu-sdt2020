(ns test.task-2-1)

(def sum
  (memoize (fn [func dx x]
             (if (= x 0.0)
               0.0
               (+ (sum func dx (- x dx)) (/ (* (+ (func x) (func (- x dx))) dx) 2))))))

(defn get-rest [x dx]
  (- x (* (Math/floor (/ x dx)) dx)))

(defn my-integration [func dx]
  (fn [x]
    (+ (sum func dx (- x (get-rest x dx))) (/ (* (+ (func x) (func (- x (get-rest x dx)))) (get-rest x dx)) 2))))

(def calc (my-integration (fn [x] (* x x)) 1))

(defn -main [& _]
  (time (calc 100))
  (time (calc 102))
  (time (calc 99)))
