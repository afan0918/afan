# Afan

English | [繁體中文](https://github.com/afan0918/Afan/blob/main/README-TW.md#afan)

## Use software to simplify the process of doing machine learning.

The project is fork from [Use-Logistic-regression-to-process-data](https://github.com/afan0918/Use-Logistic-regression-to-process-data)(in my repositories).

### Description

Afan is a light-weight and user-friendly software.

You can follow the document to learn how to use it.

#### Algorithm of machine learning

* Logistic Regression
* SVM(Support Vector Machine)

* Run all data combinations.

#### Input

![](https://i.imgur.com/PnNEBnI.png)

Input .csv file and follow the format.

You can discard features with low AUC values.

#### Output

*    model(weights)
*    roc cut-off point value

### Features

* [x] Logistic Regression
* [x] SVM(Support Vector Machine)
* [ ] KNN
* [ ] FLD
* [ ] RBFNetwork
* [ ] Others
* [x] Run all data combinations.
* [x] Export 300DPI chart graph.

### Explanation of hyperparameters

#### Logistic Regression

##### Algorithm : L-BFGS

*    MaxIter

maximum number of iterations

*    lambda(λ)

Tune the overall impact of the regularization term by multiplying its value.

Encourages weight values toward 0 (but not exactly 0)

Encourages the mean of the weights toward 0, with a normal (bell-shaped or Gaussian) distribution.

*    tolerance

To stop searching for a minimum (or maximum) 

once some tolerance is achieved, i.e. once you're close enough.

##### The result of each execution will be the same, not random.

#### SVM(Support Vector Machine)

##### Algorithm : LASVM

*    c->cost

可以當成容錯項，值越大容錯越少

須注意c值太大會容易 OverFitting

c越小會有越多 support vectors

*    tolerance

To stop searching for a minimum (or maximum) 

once some tolerance is achieved, i.e. once you're close enough.

##### Each execution result will be different

The algorithm part of machine learning use [smile](https://github.com/haifengl/smile) 。

### Contributions

Every contribution is welcome. Please refer to the contribution guidelines for more information.

MY e-mail: afan0918@g.ncu.edu.tw
