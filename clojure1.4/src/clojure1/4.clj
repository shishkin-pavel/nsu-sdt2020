(ns clojure1.4)

"rec 4"
(defn add_alphabet_to_word [started alphabet]
  (if (empty? alphabet)
    (list)
    (let [firs (first alphabet) first_in_started (first started) rest_form_started (add_alphabet_to_word started (rest alphabet))]
      (if (not= firs first_in_started)
        (cons (cons firs started) rest_form_started)
        rest_form_started))))

"rec 3"
(defn add_new_combo [started alphabet]
  (let [combinations (add_alphabet_to_word (first started) alphabet)]
    (if (empty? (rest started))
      combinations
      (concat combinations (add_new_combo (rest started) alphabet)))))

"rec 2"
(defn main_rec [started n alphabet]
  (if (= n 0)
    started
    (main_rec (add_new_combo started alphabet) (- n 1) alphabet)))

"rec 1"
(defn create_started_list [alphabet]
  (if (empty? (rest alphabet))
    (list (list (first alphabet)))
    (cons (list (first alphabet)) (create_started_list (rest alphabet)))))

(defn resolution [n alphabet]
  (let [started (create_started_list alphabet)]
    (if (= n 1)
      started
      (main_rec started (- n 1) alphabet))))

(defn -main
  "Решите задачу с помощью элементарных операций над
  последовательностями и функционалов map/reduce/filter"
  [& args]
  "решение для args = list n"
  (println (resolution (Integer/parseInt (last args)) (drop-last args))))