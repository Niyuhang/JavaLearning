## 默认参数

### Python
在python当中定义函数的参数时候，可以通过默认参数的方式,来让调用方在调用的时候可以不用传一些给定默认值的参数.

例1
``` python
def calculate_the_money(number, price, vip=False):
    """
    计算总价 如果是vip 就打85折
    """
    res = number * price
    if vip:
        res = res * 0.85
    return res
```

在例子里面，我们给函数设置了一个参数也就是vip 并且给了一个默认值是False,
在这种情况下, 如果非vip计算的时候我们就可以不用给定vip这参数了。

### Java
而在java中我们可以通过重载的方式达到相同的目的, 首先我们来了解一下什么叫做重载

#### 重载
同一个类里面可以定义相同名称的方法，但是类里面不能出现重复的方法签名(方法签名实际上是方法名+方法的参数类型)，
这些同名方法，其实就是重载。
通过重载的方式，我们就可以对一些类似的函数进行封装，也可以达到默认参数的目的。

例1
``` java
public class Market{
    public double calculateMoney(int number, double price){
        return calculateMoney(number, price, false)
    }

    public double calculateMoney(int number, double price, boolean price){
        double res = number * price
        if(vip){
            res = res * 0.85
        }
        return res
    }

}
```
在例子里面，我们定义了两个calculateMoney方法，但是他们的参数并不一样，
这样子java就会根据调用的参数，来使用相应的重载函数，并且我们也通过这方法实现了默认参数的目的。




