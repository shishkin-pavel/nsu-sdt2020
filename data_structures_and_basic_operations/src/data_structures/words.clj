(ns data_structures.words (:gen-class))

;;функция присоединяет букву i ко всем элементам coll
(defn create-word-by-letter
  ([word letter]
   (if
     (not (= (.substring word (- (.length word) 1)) letter))
     (.concat word letter)
     )
   )
  )

; some-function [a (abc)]
; concat (conj (some-function a (bc) -> (ab ac)) nil)  -> (nil ab ac)
; some-function [a (bc)]
; concat (conj (some-function a (c) -> (ac)) ab  -> (ab (ac))) -> (ab ac)
; some-function [a (c)]
; concat (conj (some-function a () -> nil) ac  -> (ac))
; some-function [a ()] -> nil
(defn create-words-by-letter
  [letter words]
  (if
    (> (count words) 0)
    (if
      (not (= (create-word-by-letter (first words) letter) nil))
      (concat (conj (create-words-by-letter letter (rest words)) (create-word-by-letter (first words) letter)))
      (create-words-by-letter letter (rest words))
      )
    )
  )


;склеивает массивы слов, где в начале стоит i буква афавита
(defn search-by-index [i alphabet words]
  (if
    (< i (count alphabet))
    (concat
      (create-words-by-letter (nth alphabet i) words)
      (search-by-index (inc i) alphabet words))
    )
  )

; берется элемент по i индексу из массива coll = алфавит (при изначальном вызове = 0)
; кладется в some-function вместе с алфавитом
; в рекурсии склеивается с some-function вместе с алфавитом, где i + 1 (проверка, что i < coll.length)
; в some-function сравнивается, что длина алфавита > 0
; склеивается элемент по i индексу и текущая голова переданного в функцию списка (если они не одинаковые)
; в рекурсию передается хвост списка
; массивы склеиваются (одна буква с остальными) *
; массивы склеиваются (остальные буквы (*) между собой)
(defn one_one
  [n alphabet words]
  (if (> n (.length (first words)))
    (one_one n alphabet (search-by-index 0 alphabet words))
    words)
  )

(defn all-words [n alphabet]
  (vec (one_one n alphabet alphabet)))
