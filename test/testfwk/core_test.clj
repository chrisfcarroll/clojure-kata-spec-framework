(ns testfwk.core-test
  (:require [testfwk.core :refer :all] 
             [testfwk.core.bootstrap :refer :all]))

(start-run)

(spec "A Spec describes expected behaviour")

(spec "A Spec runs its body" (eval bootstrap-assertion))

(spec "The Reporter should know all the results so far" 
  (assert (= 
            @results 
            {"A Spec describes expected behaviour" :manual
             "A dummy spec" :pass
             "A Spec runs its body" :pass
             }
  )))

(spec "A Spec reports its pass/fail result -- given no body"
    (assert (= :manual (spec "Spec with no body")))
    )

(spec "A Spec reports its pass/fail result -- given a passing body"
      (assert (= :pass (spec "Spec with a passing body" (assert (= 1 1)))))
      )

(spec "A Spec reports its pass/fail result -- given a failing body"
      (assert 
        (.startsWith 
          (spec "Spec with a failing body" (assert false))
          "Assert failed:"
        )))

(report-to-console)
