(ns qif-gen.qif-generator_test
  (:require [clojure.test :refer :all]
            [qif-gen.qif-generator :refer :all]))

(defn debitTransaction []
  "23/08/2015\t25/08/2015\tDEBENHAMS.COM LONDON GBR		£39.00" )

(defn creditTransaction []
  "30/04/2011\t30/04/2011 	DIRECT DEBIT - THANK YOU\t£51.00\t" )

(defn creditTransactionQifString []
  "!Type:CCard\nD30/04/2011\nPDIRECT DEBIT - THANK YOU\nM\nT£51.00\nNDeposit\nL\n^\n")

(defn debitTransactionQifString []
  "!Type:CCard\nD23/08/2015\nPDEBENHAMS.COM LONDON GBR\nM\nT£39.00\nNWithdrawal\nL\n^\n")

(deftest shouldCreateDebitTransactionMap
  (testing "Takes a debit transaction and creates a map"
    (let [transction-map (transaction-string-to-map (debitTransaction))]
      (is (= "23/08/2015" (transction-map :date)))
      (is (= "DEBENHAMS.COM LONDON GBR" (transction-map :description)))
      (is (= "£39.00" (transction-map :amount)))
      (is (= "Withdrawal" (transction-map :type))))))

(deftest shouldCreateCreditTransactionMap
  (testing "Takes a credit transaction and creates a map"
    (let [transction-map (transaction-string-to-map (creditTransaction))]
      (is (= "30/04/2011" (transction-map :date)))
      (is (= "DIRECT DEBIT - THANK YOU" (transction-map :description)))
      (is (= "£51.00" (transction-map :amount)))
      (is (= "Deposit" (transction-map :type))))))

(deftest shouldCreateDebitTransactionQifString
  (testing "Given a debit transaction map create a qif string"
    (is (= (map-to-qif-string (transaction-string-to-map (debitTransaction))) (debitTransactionQifString)))))

(deftest shouldCreateCreditTransactionQifString
  (testing "Given a credit transaction map create qif string"
    (is (= (map-to-qif-string (transaction-string-to-map (creditTransaction))) (creditTransactionQifString)))))
