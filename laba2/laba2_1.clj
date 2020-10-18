(ns laba2-1.core
  (:gen-class))


(defn mini [fun x0 x1 dx]
  (* (* (+ (fun x0) (fun x1)) 0.5) dx))


(defn integral [fun x1 dx]
  (if (not= x1 0)
    (let [x0 (- x1 dx)] (+ (mini fun x0 x1 dx)) (integral fun x0 dx))
    0))

(def integral-m (memoize integral))

(def f #(* % %))
(defn -main []
  (time (integral f 100 1))
  (println "---------1")
  (time (integral-m f 100 1))
  (time (integral f 100 1))
  (println "--------2")
  (time (integral f 120 1))
  (time (integral f 150 1)))
(-main)
