# machine-learning

## 用軟體來簡化做機器學習的過程

### 目前功能

#### 運算

能夠快速的使用logistic regression(邏輯回歸)、SVM(支持向量機)來快速進行模型訓練，不必與程式碼進行交互。

#### 輸入資料

![](https://i.imgur.com/PnNEBnI.png)

#### 輸出資料

*    model(weights)
*    ROC Curve(數據圖)
*    roc cut-off point value
*    預測結果(可加)

### 未來可加入選項

*    KNN
*    LDA
*    QDA
*    others.....

### 參數說明

#### logistic regression(邏輯回歸)

運行內核:L-BFGS

*    MaxIter 最大迭代次數

maximum number of iterations

*    lambda(λ)

Tune the overall impact of the regularization term by multiplying its value.

Encourages weight values toward 0 (but not exactly 0)

Encourages the mean of the weights toward 0, with a normal (bell-shaped or Gaussian) distribution.

*    tolerance

To stop searching for a minimum (or maximum) 

once some tolerance is achieved, i.e. once you're close enough.

#### SVM(支持向量機)

運行內核:LASVM

*    c->cost

可以當成容錯項，值越大容錯越少

須注意c值太大會容易 OverFitting

c越小會有越多 support vectors

*    tolerance

To stop searching for a minimum (or maximum) 

once some tolerance is achieved, i.e. once you're close enough.

越大，表示在容忍範圍內的誤差/分錯的資料，不會被懲罰

反之，越接近0，每一個誤差/分錯的資料都會被懲罰。

fork from smile.
