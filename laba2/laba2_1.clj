(ns laba2-1.core
  (:gen-class))

(defn mini [fun x0 x1 dx]
  (* (* (+ (fun x0) (fun x1)) 0.5) dx))


(defn integral [fun x1 dx]
  (let [x0 (- x1 dx)]
   (if (not= x1 0) (+ (mini fun x0 x1 dx) (integral fun x0 dx))
    (mini fun x0 x1 dx))
    )
  )

(def integral-m (memoize integral))

(def f #(* % %))
(defn -main []
  (time (integral f 1000 1))
  (println "---------1")
  (time (integral-m f 1000 1))
  (time (integral-m f 1000 1))
  (time (integral-m f 1200 1)))
(-main)