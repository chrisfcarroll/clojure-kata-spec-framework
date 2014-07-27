(ns testfwk.core)

(defmacro spec
  "Execute a specification and reports failure"
  [name & more]
  (cons 'do more))

(ns testfwk.core.bootstrap (:require [testfwk.core :refer :all]))
 
  (def bootstrap-assertion 
    '(let  [output  (with-out-str (spec "A dummy spec" (print "I was run")))]
       (assert (= output "I was run"))
       true))
  (if (eval bootstrap-assertion)
    (println "Bootstrapped OK.")
    (throw (AssertionError. "Bootstrap assertion failed. Don't believe anything I claim has passed.")))
 
