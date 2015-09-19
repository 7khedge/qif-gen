(ns qif-gen.transaction-file-test
  (:require [clojure.test :refer :all]
            [qif-gen.transaction-file :refer :all]))

(def trasaction-file-path
  "/Users/adityasofat/workspace/qif-gen/resources/transaction.txt")

(def qif-file-path
  "/Users/adityasofat/workspace/qif-gen/resources/transaction.qif")

(deftest shouldReadTransactionFile
  (testing "Read Transaction file into vector"
    (is (= (count (readTransactions trasaction-file-path)) 81))))

(deftest shouldConvertToQifFilename
  (testing "Replace file extension with qif"
    (is (= (qifFilename trasaction-file-path) qif-file-path))))

