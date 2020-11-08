(ns lab_2.task_2_2)

(defn get-rest [func x dx]
  (let [x-rest (- x (* (Math/floor (/ x dx)) dx))]
    (/ (* (+ (func x) (func (- x x-rest)) x-rest) 2))))

(defn my-integration [func dx]
  (let
    [sum (map first (iterate (fn [[acc xi]] [(+ acc (/ (* (+ (func xi) (func (- xi dx))) dx) 2)) (+ xi dx)]) [0 dx]))]
    (fn [x]
      (+ (nth sum (Math/floor (/ x dx))) (get-rest func x dx)))))

(def calc (my-integration (fn [x] (* x x)) 1))

(defn -main [& _]
  (time (calc 100000))
  (time (calc 100002))
  (time (calc 9999)))
