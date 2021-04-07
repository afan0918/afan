# Afan

因為教授說可以拿名字當名稱我就這樣了

## 用軟體來簡化做機器學習的過程

### 目前功能

#### 運算

能夠快速的使用logistic regression(邏輯回歸)、SVM(支持向量機)來快速進行模型訓練，不必與程式碼進行交互。
找出ROC cut-off point(糟糕我寫到這裡突然發現忘記匯出給使用者)與能夠方便的以ROC cut-off point對數據資料做初篩。
能夠輕鬆高效的把所有數據資料的組合跑過一遍機器學習

#### 輸入資料

![](https://i.imgur.com/PnNEBnI.png)

#### 輸出資料

*    model(weights)
*    roc cut-off point value

### 未來可加入選項

雖然是可加入，但我先把圖畫完再說。

*    KNN(
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

每次執行結果會一樣，不具隨機性。

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

每次執行結果不一樣，具隨機性。

機器學習的算法部份一開始我自己寫，後來因為效能考量和我覺得照我的算法來寫還需要自己證明正確性，所以改引用 [smile](https://github.com/haifengl/smile) 。

因為我只會寫logistic regression跟SVM，所以目前也只有這兩個，之後我多加幾個。

#### 作者好爛，我要自行編譯改寫

好~，不過建議lib直接用我整理好的.jar包，因為取資料需求，我有改寫SVM部分的smile-core然後重新封裝，所以如果直接下載 [smile](https://github.com/haifengl/smile)的話
，可能SVM部分會壞掉，須注意。

#### 版本概述

1.0.0 2MB可以跑多種機器學習還可以生出相對應數據圖，也太香了八

1.1.0 乾變成了87MB，睡前懶得壓大小了，之後再說，抱歉了

1.1.1 解決忘記匯出ROC curve資料的問題，不小心耍低能

1.1.2 除了最後一張折線圖的panel一直顯示不出來，一切都好，氣死，我花了兩天，從直接暴力手畫到引用xchart一直都沒有解決問題，好絕望，我可以搞定的對吧。
