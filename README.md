# Afan

因為教授說想不到名字，也可以拿自己的名字當名稱，我就這樣了。

不要太在意啦，我的第一個寫破萬行很多很多的project餒，至少紀念一下，夢開始的地方。

## 用軟體來簡化做機器學習的過程

### 緣起

上個學期修了中央大學生醫系彚音老師的課，剛好老師給了我這個題目當期末專題，就一路慢慢看論文報paper討論想法，驗證資料的做下去了。

此項目的前身是用logistic regression做機器學習，也在我的repositories裡面，現在大概就是進階版的感覺，優化UI和運行效率還有擴充大量功能，我盡量讓它趨近完善。

### 目前功能

#### 運算

能夠快速的使用logistic regression(邏輯回歸)、SVM(支持向量機)來快速進行模型訓練，不必與程式碼進行交互。
找出ROC cut-off point(糟糕我寫到這裡突然發現忘記匯出給使用者)與能夠方便的以ROC cut-off point對數據資料做初篩。
能夠輕鬆高效的把所有數據資料的組合跑過一遍機器學習

#### 輸入資料

![](https://i.imgur.com/PnNEBnI.png)

檔案類型為.csv檔，選擇要匯入的檔案時也會只給你選該類型檔案名稱。

#### 輸出資料

*    model(weights)
*    roc cut-off point value

### 未來可加入選項

目前應該會先把二分類式我會做的機器學習先弄完，先單純做二分類是因為這樣比較好繪圖和有整合性的跑。

之後再擴展到多分類。

*    KNN(只能預測，沒有權重，因為KNN就是拿其他數據當Model的算法，而且大測資速度會比我特別找每次update只要一行的LR或SVM慢很多，可能不太適合生醫方面的大量資料分析)
*    FLD
*    RBFNetwork(我只有先做二分類)
*    (以上皆已經寫好測試程式，只是寫介面...麻...煩，可能先把可以匯出和畫出的圖做好，畢竟有的畫不出圖最後還要拿掉，介面太常增刪整改我會死掉)
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

### 作者好爛，我要自行編譯改寫

好~歡迎fork，不過建議lib直接用我整理好的.jar包，因為取資料需求，我有改寫SVM部分的smile-core然後重新封裝，所以如果直接下載 [smile](https://github.com/haifengl/smile)的話
，可能SVM部分會壞掉，須注意。

需要什麼功能或算法也可以聯繫我，我的e-mail: afan0918@g.ncu.edu.tw ，歡迎大家的想法，我盡量做，其實加算法比加圖簡單多了(因為不小心又要改動介面，氣死)，只是我小菜逼八，可能更新速度不快。

### 版本概述

1.0.0 2MB可以跑多種機器學習還可以生出相對應數據圖，也太香了八

1.1.0 乾變成了87MB，睡前懶得壓大小了，之後再說，抱歉了

1.1.1 解決忘記匯出ROC curve資料的問題，不小心耍低能

1.1.2 除了最後一張折線圖的panel一直顯示不出來，一切都好，氣死，我花了兩天，從直接暴力手畫到引用xchart一直都沒有解決問題，好絕望，我可以搞定的對吧。\

1.1.3 我搞不定，內嵌進介面的Panel一直無法進行重繪，所以我小改了一下使用者介面，成功繞過去，應該算得上是完整版了。
