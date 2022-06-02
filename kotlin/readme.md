# kotlin中的协程
## 同步的概念
> 同步指的是一个行为，当执行IO操作的时候，在代码层面我们需要主动去等待结果，直到结果返回。

## 阻塞的概念
> 而阻塞指的是一种状态 ,当执行IO操作的时候，线程处于挂起状态，就是该线程没有执行了。
> 所以同步不是阻塞，同步也可以是非阻塞的，比如我们在执行同步代码块的时候，线程可以不阻塞，而是一直在后台运行


## 协程
> 解决了传统方式中使用的多线程阻塞的问题。协程建立在线程上。
> 也解决了传统方式使用多线程来控制并发的问题，使用协程也可以解决并发的问题


## 协程2
> 协程不是线程，也不是新版本的线程，它是基于线程封装的一套更上层`工具库`，
> 我们可以使用kotlin协程库提供的api方便的灵活的指定协程中代码执行的线程、切换线程，
> 但是不需要接触线程Thread类。说到这里，大家可能就会想到Android的AsyncTask或者RxJava的Schedulers，
> 没错，从某种意义上来说它们和协程是相通的，都解决了异步线程切换的问题，
> 然而协程最重要的是通过`非阻塞挂起`和`恢复`实现了`异步代码的同步编写方式`，
> 把原本`运行在不同线程的代码`写在一个`代码块{}`里，看起来就`像是同步代码`。

```kotlin
MainScope().launch(){     //在UI线程中启动协程
    //下面的代码看起来会以同步的方式一行行执行（异步代码同步获取结果）
    val token = apiService.getToken()   // 网络请求：IO线程，获取用户token
    val user = apiService.getUser(token)// 网络请求：IO线程，获取用户信息
    nameTv.text = user.name             // 更新 UI：主线程，展示用户名
    val articles = apiService.getUserArticle(user.id)// 网络请求：IO线程，根据用户id获取用户写的文章
    articleTv.text = "用户${user.name}总共写了${articles.size}篇文章"   // 更新 UI：主线程
}
```

> 协程并不是从操作系统层面创立的新的运行方式，代码是运行在线程中的，线程又是运行在进程中的，协程也是运行在线程中的，
> 所以才说它是基于线程封装的库。然而有人会拿协程与线程比较，问协程是不是比线程效率更高？
> 如果理解了协程是基于线程封装就应该知道，协程并没有改变代码运行在线程中的原则，单线程中的协程执行时间并不会比不用协程少，
> 它们之间`没有可比性`，因为它们根本不属于同一类事物；协程也`不是为了线程而生的`，它是`为了解决因为多线程带来的编码上的不便的问题而出现的。`



> 我们定义接口的时候使用了suspend关键字，它的意思是`挂起、暂停`，函数被加了这个标记就称它为挂起函数，
> 需要注意的是，suspend关键字并没有其他重要的作用，它仅仅标识某个函数是挂起函数，可以在函数中调用其他挂起函数，
> 但是只能在协程中调用它。所以上面两个接口都被定义为挂起函数了，挂起函数只能在协程中调用，那谁是协程？
> 其实在kotlin协程库中是有一个类AbstractCoroutine来表示协程的，这个抽象类有很多子类代表不同的协程，
> 但是这些子类都是`private`的，并没有暴露给我们，所以你在其他文章中看到别人说viewModelScope.launch{}包裹起来的`闭包(代码块)就是协程`也没问题，
> 但这个代码块的真正意义是`协程需要执行的代码`。当在协程中`调用到挂起函数时`，`协程就会在当前线程（主线程）中被挂起`，
> 这就是协程中著名的`非阻塞式挂起`，`主线程暂时停止执行这个协程中剩余的代码`，注意：暂停并`不是阻塞等待`（否则会ANR），
> 而是`主线程暂时从这个协程中被释放出来去处理其他Handler消息`，比如响应用户操作、绘制View等等。 

> 那挂起函数谁执行？这得看挂起函数内部是否有切换线程，如果没有切换线程当然就是主线程执行了，
> 所以`挂起函数不一定就是在子线程中执行的`，但是通常在定义挂起函数时都会为它指定其他线程，
> 这样挂起才有意义。比如上面定义的suspend的请求接口，Retorift在执行请求的时候就切换到了子线程并挂起主线程，
> 当请求完成（挂起函数执行完毕）返回结果时，会通知主线程：我该干的都干完了，下面的事你接着干吧，
> 主线程接到通知后就会拿到挂起函数返回的结果继续执行协程里面剩余的代码，这叫做`协程恢复(resume)`。
> 如果又遇到挂起函数就会重复这个过程，直到协程中的代码被执行完。

> 通过协程可以彻底去除回调，使用同步的方式编写异步代码。什么是同步调用？调用一个方法能直接拿到方法的返回值，
> 尽管这个方法是耗时的、在其他线程执行的，也能直接得到它的返回值，然后再执行下面的代码，协程不是通过等待的方式实现同步，
> 而是通过`非阻塞挂起实现看起来同步的效果`。

> 消灭回调只是利用协程实现的一个很小的部分 。其实上面第一部分讲协程是什么的时候就已经说了，
> 协程是为了让我们`更方便的写并发代码`，把原本运行在不同线程的代码写在一个代码块{}里，看起来就`像是同步代码`。，
> 它的侧重点是`多线程并发`和`看起来同步`。所以对于串行（上述嵌套）或者并行（多个接口并行请求）的代码都可以用协程来"同步"调用的，
> `不管业务有多复杂，都可以用协程写出清晰的上下行代码结构`。

> 概括起来其实就是使用kotlin协程让代码更简洁。根本原因是解决了异步任务非阻塞同步返回数据的问题，
> `达到简化代码逻辑`、所见即所得的目的。所以我们要先搞懂什么是`异步任务、以及异步任务数据返回`



## 相关概念 及术语

### 协程
> 协程是可挂起计算的实例，它需要一个代码块(挂起lambda表达式)运行，并具有类似的生命周期（可以被创建、启动和取消），
> 它不绑定到任何特定的线程，可以在一个线程中挂起其执行，并在另一个线程中恢复，它在完结时可能伴随着某种结果（值或异常）。 
> 在Kotlin协程库中，所有的协程都是抽象类kotlinx.coroutines.AbstractCoroutine的子类，在使用协程编写代码时，
> 我们不会接触到该类，因为根本不需要我们关心协程是怎样执行、怎样调度的。
> 我们需要做的就是告诉协程库，我需要`开启协程`了、我要在`哪个线程中执行挂起函数`、我需要在`什么时候取消协程`，
> Kotlin协程库为我们暴露了协程上下文`CoroutineContext`、协程作用域`CoroutineScope`、
> 协程工作`Job`来完成这些工作，这三个类就是协程库暴露给我们的API，了解这3个类就能玩转协程绝大部分的使用场景。



### 协程构建器
> 使用一些“挂起lambda表达式”作为参数来创建一个协程的函数称为协程构建器，并且可选地，
> 还提供某种形式以访问协程的结果。kotlin协程库提供了几个构建器如launch{}、async{}等用于构建协程



### 挂起函数
> 使用`suspend`修饰符标记的函数。它可能会`通过调用其他挂起函数挂起执行代码，而不阻塞当前执行线程`。
> 挂起函数不能在常规代码中被调用，只能在其他挂起函数或挂起lambda表达式中。
> 标准库提供了原始的挂起函数，用于定义其他所有挂起函数。协程之所以能用同步的方式编写异步代码，
> 正是`由于挂起函数的非阻塞挂起`，说的通俗一点，`协程在调用到挂起函数时，会挂起当前状态`，
> 让当前线程去做别的事情，`而挂起函数通常（但不一定）会切到别的线程执行`，
> 当`挂起函数执行完毕会将结果值或者异常对象带到当前线程`，`恢复当前线程`的状态让它`继续执行协程中的代码`

### 挂起函数类型

> 表示挂起函数以及挂起lambda表达式的函数类型。它就像一个普通的函数类型，但具有suspend修饰符。
> 举个例子，suspend () -> Int是一个没有参数、返回Int的挂起函数的函数类型。一个声明为suspend fun foo() : Int
> 的挂起函数符合上述函数类型。launch()函数接受一个挂起函数类型的参数block

```kotlin
public fun CoroutineScope.launch(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit   //接受一个 挂起函数类型 作为参数
): Job {
   
}
```

### 挂起lambda表达式
> 如果函数的最后一个参数是函数类型，调用该函数时需要传入一个函数类型实例，由于函数最后一个参数是函数类型或者函数接口，
> 调用它时可以将函数类型实例写在方法体外，这就是普通的普通的lambda表达式。挂起lambda表达式与普通lambda表达式
> 在形式上是一样的，不同的是它的函数类型被suspend修饰符标记。就像常规lambda表达式是匿名局部函数的短语法形式一样，
> 挂起lambda表达式是匿名挂起函数的短语法形式。我们可以在挂起lambda表达式中调用其他挂起函数挂起执行代码，
> 而不阻塞当前执行线程。

```kotlin
    /**
     * launch()函数接受一个挂起函数类型作为参数，在调用lacunch函数是使用lambda表达式的形式
     * 由于这个lambda表达式对应的函数类型是挂起函数类型，所以称这个lambda表达式为挂起lambda表达式
     */
    GlobalScope.launch {   //挂起lambda表达式
        //调用其他挂起函数
    }
```

### 挂起作用域
> 挂起作用域是指挂起函数的函数体之内的区域，通俗的讲就是挂起函数的{}括起来的区域就是挂起作用域。只有在这个区域内才能调用挂起函数。如下：
```kotlin
    GlobalScope.launch {   //launch()接受一个挂起函数类型作为参数，所以lambda表达式{}里面就是挂起作用域
        //挂起作用域
    }

    //挂起函数
    suspend fun mySuspendFun() : Int = withContext(Dispatchers.IO){
        //挂起作用域
    }
```

### 挂起点
> 协程执行过程中可能被挂起的位置，从语法上说，挂起点是对一个挂起函数的调用，但实际的挂起在挂起函数调用了标准库中的原始挂起函数时发生。挂起的原理是函数return了一个特殊的COROUTINE_SUSPENDED标志


### 续体
> 是挂起的协程在挂起点时的状态，它在概念上表示在挂起点之后的剩余应执行的代码。已经创建，但尚未启动的协程，由它的初始续体表示，这由它的整个执行组成，类型为Continuation


### 协程构建器
> 如果要调用挂起函数，首先得通过协程构建器构建协程，因为挂起函数的调用源头只能是协程代码块中。
> 标准库提供了用于在常规非挂起作用域中启动协程执行的函数，这些函数称为协程构建器。
> 在点击查看GlobalScope.launch()函数源码时，发现这个函数定义在kotlinx.coroutines.Builders.common.kt文件中，
> 定位发现它编译后的class文件为kotlinx.coroutines.BuildersKt.class


BuildersKt.class内容如下：
```kotlin
package kotlinx.coroutines

public fun <T> runBlocking(context: kotlin.coroutines.CoroutineContext /* = compiled code */, block: suspend kotlinx.coroutines.CoroutineScope.() -> T): T { contract { /* compiled contract */ }; /* compiled code */ }

public suspend fun <T> withContext(context: kotlin.coroutines.CoroutineContext, block: suspend kotlinx.coroutines.CoroutineScope.() -> T): T { contract { };}

public fun <T> kotlinx.coroutines.CoroutineScope.async(context: kotlin.coroutines.CoroutineContext , start: kotlinx.coroutines.CoroutineStart, block: suspend kotlinx.coroutines.CoroutineScope.() -> T): kotlinx.coroutines.Deferred<T> { }

public suspend inline operator fun <T> kotlinx.coroutines.CoroutineDispatcher.invoke(noinline block: suspend kotlinx.coroutines.CoroutineScope.() -> T): T {  }

public fun kotlinx.coroutines.CoroutineScope.launch(context: kotlin.coroutines.CoroutineContext , start: kotlinx.coroutines.CoroutineStart , block: suspend kotlinx.coroutines.CoroutineScope.() -> kotlin.Unit): kotlinx.coroutines.Job { }

```

> 从类名BuildersKt可以看出这是协程的构建器对应的类，一共有5个函数，都可以用来构建一个新的协程，
> 其中runBlocking()、withContext()是顶层函数，这两个函数可以直接调用(不需要创建对象)，
> 但是withContext是一个suspend挂起函数，它只能在协程或其他挂起函数中调用(必须先有协程)。
> invoke函数是CoroutineDispatcher(协程调度器)的扩展，它也是一个suspend函数，同样也是需要先有协程。
> launch()和async()是CoroutineScope接口的扩展函数，需要使用CoroutineScope的实例对象调用。

> 注意：所有的构建器其实都是函数，下面的讲解中可能会出现如launch()或者launch{}的形式其实都是指launch()函数的构建器。
> 只是不同的语言场景表现形式不一样罢了，如果把它当成构建器偏向于写成launch{}的形式，将它描述为函数是将写为launch()


如果要在非挂起作用域中启动协程，有3种构建器：
#### runBlocking{}
函数的声明头：
```kotlin
public fun <T> runBlocking(context: CoroutineContext = EmptyCoroutineContext, block: suspend CoroutineScope.() -> T): T
```
> 这个函数创建的协程会阻塞当前线程，直到协程代码块执行完毕，所以通常用于main函数或者其他测试用例中.
> 因为在main函数中启动一个协程去执行耗时任务，如果不阻塞main函数的线程，main函数执行完jvm就退出了，
> 为了避免jvm退出，通常在最后需要Thread.sleep(Long.MAX_VALUE)让主线程休眠来等待协程执行完毕。
> 但是如果使用runBlocking{}创建协程就不会出现jvm提前退出的问题



```kotlin
fun main() {
    // 使用runBlocking顶层函数创建协程
    runBlocking {
       
    }
}
```

#### launch{}
> launch()函数在不阻塞当前线程的情况下启动新的协程，并将对协程的引用作为Job返回，可以通过调用Job.cancel()取消协程。
> 需要注意的是通过GlobalScope创建的协程是全局范围的协程，是顶层协程，其生命周期与应用程序同步。
> 也就是说即便协程执行完毕，但是应用程序没有关闭，协程还是会继续运行着，如果我们不停的创建很多顶层协程，
> 虽然它是轻量级的但仍然会消耗一些内存资源，所以如果要创建顶层协程，通常需要手动保持Job引用，
> 在合适的时机调用job.cancel()退出这些协程。
> 
```kotlin
//使用GlobalScope单例对象调用launch
fun main() {
    val job = GlobalScope.launch {
        //调用其他挂起函数
        for(i in 1..5){
            delay(1000)   //每隔1s打印一个数字
            println("--->$i")
        }
    }

    Timer().schedule(object:TimerTask(){
        override fun run() {
            println("-----------------------------3s时间到----------------------------")
            println("协程是否活动？${job.isActive}")          //true
            println("协程是否执行完毕？${job.isCompleted}")   //false
            println("协程是否取消？${job.isCancelled}")       //false
            job.cancel("中途取消协程，生命周期结束")
            println("协程是否活动？${job.isActive}")          //false
            println("协程是否执行完毕？${job.isCompleted}")   //false
            println("协程是否取消？${job.isCancelled}")      //true
        }
    }, 3000)   //3s后结束协程
    Thread.sleep(100000)  //阻止jvm退出
}

```

#### async{}
```kotlin
// 使用 GlobalScope单例对象调用async
fun main() {
    GlobalScope.async {
        //调用其他挂起函数
        delay(1000)
        println("GlobalScope.async")
    }
}
```

> async{}和launch{}是差不多的，默认情况下，创建的协程都会立即执行，不同的是这两个函数返回类型不同，
> launch返回的是Job，可以通过Job取消协程，而async返回的是Deferred类型，Deferred是Job的子类，
> 所以同样可以cancel协程，但是它是一个有结果的Job，也就是说async{}可以返回一个值，这个值将保存在Deferred中，
> 可以调用Deferred.await()获取协程的返回值，而**await()是一个挂起函数，只能在挂起作用域内调用，
> 所以通常不用async{}来创建最外层的协程**，因为非挂起作用域无法调用await()函数获取协程的返回值，
> 所以返回值没有意义，这样的话async()的返回值Deferred就是普通的Job，
> 所以完全可以使用launch{}代替async{} async{}通常用于在挂起作用域中构建并发的子协程，
> 这些子协程作业将并发执行，但是可以等待并发协程都返回数据后，合并处理结果，这种需求在开发中是非常常见的：

```kotlin
fun main() {
    val job = GlobalScope.launch {
        //通过async{}构建的协程默认会立即执行，因为默认启动模式为CoroutineStart.DEFAULT
        val deferred1 = async(start = CoroutineStart.DEFAULT){
            delay(1000)
            println("1 子协程返回数据")
            "deferred1"
        }
        val deferred2 = async{
            delay(2000)
            println("2 子协程返回数据")
            "deferred2"
        }
        //async{}的执行结果被封装在Deferred对象中，需要调用await()获取结果值
        val result1 = deferred1.await()  //获取第1个子协程的返回值
        val result2 = deferred2.await()  //获取第2个子协程的返回值
        println("返回数据:$result1 - $result2")   //合并两个返回结果 deferred1 - deferred2
    }
    Thread.sleep(100000)  //阻止jvm退出
}
```




####  怎样获取CoroutineScope的实例对象？
> launch()和async()构建器是`CoroutineScope接口的扩展函数`，只有CoroutineScope的子类对象才能调用这两个函数。
> kotlin协程核心库中只暴露了GlobalScope这一个子类单例给我们，所以上面的示例中我就直接使用了这个对象创建协程，
> 但是GlobalScope全局作用域创建的协程即使执行完毕也不会退出，会引发内存泄漏，使用局限性太大，
> 难道就没有其他子类对象可使用了吗？

```kotlin
public fun <T> CoroutineScope.async(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> T
): Deferred<T>{
    
}
```

```kotlin
public fun CoroutineScope.launch(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
): Job{
    
}

```

> 协程库还提供了MainScope()和CoroutineScope(context:CoroutineContext)两个工厂函数来获取一个CoroutineScope实例对象。
> 当然我们还可以通过实现CoroutineScope接口自定义作用域类然后创建它的对象：

```kotlin
/**自定义协程作用域类*/
class MyCoroutineScope(context: CoroutineContext) : CoroutineScope {
    override val coroutineContext: CoroutineContext = context
}
fun main() {
    //构建一个自定义作用域对象
    val coroutineScope = MyCoroutineScope(Dispatchers.Unconfined)
    //调用launch函数创建协程
    coroutineScope.launch {
        delay(1000)
        println("协程是在${Thread.currentThread()}执行的")
    }
    Thread.sleep(100000)  //阻止jvm退出
}


```


#### 其他构建器(构建子协程)
>上面几种方式可以在非挂起作用域中启动协程（构建最外层协程），如果是在挂起作用域内，
> 还可以通过其他构建器创建子协程，如withContext()和coroutineScope()。
> 严格的说来这两个函数称为作用域函数，只是它们真的可以创建子协程，所以这里暂且给它们一个协程构建器的名分吧：

```kotlin
GlobalScope.launch() {  
    //1. 构建子协程: 通常用于切换线程、或者自定义挂起函数
    withContext(Dispatchers.IO){}
    //2. 构建子协程: 通常用于分担父协程任务(将作业任务分块)
    coroutineScope{}
    //3. 构建子协程: 通常用于构建并发任务,等待结果合并
    val deferred = async {  }
   
}
```



# suspend挂起函数&挂起和恢复的实现原理

## 1. 自定义挂起函数
> 函数是对为实现某个功能或者计算某个结果的多行代码的封装，挂起函数也是一样，
> 与普通函数不同的是挂起函数"通常"被放到其他线程(异步)，并且能在不阻塞当前线程的情况下同步的得到函数的结果。
> 不阻塞当前线程就是挂起，它指的是当协程中调用挂起函数时会记录当前的状态并挂起(暂停)协程的执行（释放当前线程），
> 以达到非阻塞等待异步计算结果的目的。说白了就是不阻塞协程代码块所在的线程但暂停挂起点之后的代码执行，
> 当挂起函数执行完毕再恢复挂起点后的代码执行。比如下面示例中，在主线程开启一个协程，调用挂起函数delay()延迟1s后在更新UI，
> 与Thread.sleep不同的是delay不会阻塞主线程，这个延迟动作是在子线程中完成的。

```kotlin
fun main() {
    val launch = GlobalScope.launch {
        delay(1000)// 非阻塞的等待 1 秒钟（默认时间单位是毫秒）
        println("kotlin coroutines")// 在延迟后打印输出
    }
    Thread.sleep(1000)
}
```

### 1.1 为什么需要自定义挂起函数
> 函数的作用就是对功能的封装，比如从服务器获取用户信息、将数据存在在本地数据库等都可以被封装成一个函数，
> 如果把这个函数定义为普通的函数，在调用这些函数时就会阻塞当前线程（当前线程去执行这个函数就不能干别的事情了）。
> 如果将这些函数定义为挂起函数，这些步骤就可以让协程自动帮我们完成了，而我们关注的侧重点是函数功能代码的封装。
> 挂起函数的目的是用来挂起协程的执行等待异步计算的结果，所以一个挂起函数通常有两个要点：挂起和异步，接下来我们一步步来实现自定义挂起函数


### 1.2 suspend到底有什么用？
所有的挂起函数都由suspend关键字修饰，是不是有suspend修饰的函数都是挂起函数？答案是NO，比如：
```kotlin
data class User(val name: String)

suspend fun getUser():User{
    return User("bh")
}
```
> getUser()函数有suspend修饰，但是IDE提示Remove redundant suspend modifier移除冗余的suspend修饰符，为什么呢？
> 我们先搞清楚suspend到底是什么？它有什么作用？
> suspend是kotlin中的修饰符，kotlin源码最终都将被编译为java的class执行，而java中并没有这个修饰符，所以suspend仅仅在编码和编译阶段起作用：
> 在编码阶段：suspend仅仅作为一个标志，表示这是一个挂起函数，它只能在协程或者其他挂起函数中被调用，
> 如果在普通函数中调用IDE会提示错误；并且它可以调用其他挂起函数,
> 在编译阶段：由suspend修饰的函数被编译为class后，函数会被增加一个Continuation(续体)类型的参数

```java
  public static final Object getUser(@NotNull Continuation $completion) {
      String var1 = "假的挂起函数" + Thread.currentThread()";
      System.out.println(var1);
      Thread.sleep(1000L);
      return new User("bh");
   }
```

> 对于jvm来说，这就是一个参数为Continuation类型的普通函数，这个参数在函数体中并没有被使用，所以是一个多余的参数，
> 而suspend的作用就是在编译时增加这个参数，所以suspend修饰符就是多余的。

> 怎样让suspend修饰符不多余？就是在函数体类要使用Continuation类型的参数，而这个参数是编译器自动添加的，
> 在编码阶段肯定是没办法使用，只能在运行阶段去使用，怎样在运行阶段使用它呢？
> `答案就是调用协程库提供的挂起函数`。要真正实现挂起，必须调用一些`协程库中定义的顶层挂起函数`，
> `只有这些库自带的挂起函数才能真正实现协程的挂起`，而`调用他们的地方才是真正的挂起点`
> (真正的挂起操作是这些顶层挂起函数内部调用了`trySuspend()`并返回了`COROUTINE_SUSPENDED`标志使得当前线程退出执行从而挂起协程)。


### 1.3 不完全挂起函数（组合挂起函数）
> 为了真正挂起协程就要调用协程库中的挂起函数，协程库的挂起函数很多，是不是随便调用一个就ok呢？比如：
```kotlin
suspend fun getUser():User{
	//调用自带的挂起函数实现挂起
	delay(1000)          //真正的挂起点
	//以下为函数真正的耗时逻辑
    Thread.sleep(1000)        //模拟耗时
    return User("bh")
}
```
> 在getUser()中调用了delay()，IDE不再提示suspend多余(通过查看反编译后的java代码发现Continuation参数确实在函数体中被使用)，
> 但是这个挂起对getUser()并没有意义，我们分析getUser()的执行，首先在挂起作用域中调用这个函数，
> 函数体第一句调用了delay()挂起了协程，协程所在的线程(当前线程)将会停止继续执行(非阻塞)，直到1s延迟完成，
> 协程将恢复当前线程继续执行下面的函数代码，也就是说函数体一部分耗时计算不是在协程被挂起的状态下执行的，
> 而是直接运行在协程所在的线程(执行式阻塞当前线程)，这种函数称为不完全挂起函数。


### 1.4 真正的、完全的挂起函数
协程库定义了以下顶层挂起函数方便我们自定义挂起函数：
```kotlin
//①. 不常用
public suspend inline fun <T> suspendCoroutine(crossinline block: (Continuation<T>) -> Unit): T
//②. 常用
public suspend inline fun <T> suspendCancellableCoroutine(
    crossinline block: (CancellableContinuation<T>) -> Unit
): T
```
> 这两个函数的作用是捕获当前的协程的续体对象（下面会讲到的SuspendLambda对象）作为参数，
> 其实是SuspendLambda中调用到挂起函数时将this作为参数传入的。通常被用于定义自己的挂起函数。
> 它们都调用了另一个顶层挂起函数suspendCoroutineUninterceptedOrReturn()用于对参数续体对象进行包装，
> 然后执行作为参数传入的代码块block，在等待恢复信号期间(代码块在未来某一时刻调用续体的resume系列方法)挂起协程的执行。

> 这两个函数的区别是，suspendCancellableCoroutine()函数会用将续体对象拦截包装为一个CancellableContinuation类型，
> CancellableContinuation是一个可以cancel()取消的续体，用于控制协程的生命周期。
> 尽管协程库提供了不可取消的suspendCoroutine()函数，但推荐始终选择使用suspendCancellableCoroutine()
> 处理协程作用域的取消，从底层API取消事件传播。

下面我们就通过调用suspendCancellableCoroutine改造一下自己的挂起函数：
```kotlin
//调用suspendCancellableCoroutine()，将函数体作为参数传入
suspend fun getUser(): User = suspendCancellableCoroutine {
	//被拦截后的可取消续体对象
    cancellableContinuation ->
    println("挂起函数执行线程${Thread.currentThread()}") //Thread[main,5,main]
    Thread.sleep(3000)
    cancellableContinuation.resume(User("openXu"))
    cancellableContinuation.cancel()
}

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
	//在主线程中开启一个协程
	CoroutineScope(Dispatchers.Main).launch{
	    showProgressDialog()       //UI：显示进度框
	    val user = getUser()       //挂起点
	    tv.text = user.name        //更新UI
	    dismissProgressDialog()    //UI：隐藏进度框
	}
}
```
> getUser()函数直接被赋值为协程库提供的挂起函数，函数体是作为参数传入的，
> 这样调用getUser()的地方就相当于调用了suspendCancellableCoroutine()，
> 会立马挂起协程，这样getUser()才是真正的、完全的挂起函数。
> 上述示例是在Activity环境中，在UI线程开启一个协程后调用挂起函数getUser()，并且在之前和之后显示和隐藏进度圈，
> 运行项目可以观察到进度圈显示后，卡顿了3s然后隐藏。


> 不是说挂起不会阻塞当前线程吗？为什么还会卡顿？`因为我们并没有指定挂起函数执行的线程，`
> 默认就在当前UI线程调度了，就相当于在UI线程进行了耗时操作。目前我们的自定义挂起函数只是实现了挂起，
> 但这个`挂起并没有太大意义`，`因为是单线程的`，所以为了`实现挂起不阻塞主线程`，还缺少`异步`。
> !!!! `挂起函数不一定是在子线程执行的`!!!!!!


### 1.5 异步挂起函数
> 我们对getUser()函数进行改造，在black代码块中创建一个子线程，使得函数体代码运行在子线程中，运行项目就不会出现卡顿了：
```kotlin
suspend fun getUser(): User = suspendCancellableCoroutine {
    cancellableContinuation ->
    //创建子线程实现异步
    Thread {
        try {
            Thread.sleep(3000)
            when(Random.nextInt(10)%2){ 
                0->{ //10以内随机数如果是偶数返回成功
                    cancellableContinuation.resume(User("openXu"))
                    cancellableContinuation.cancel()
                }
                1-> throw Exception("模拟异常")
            }
        }catch (e:Exception){
        	//通过resumeWithException()用一个异常恢复协程执行
            cancellableContinuation.resumeWithException(e)
        }
    }.start()
}

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
	//在主线程中开启一个协程
	CoroutineScope(Dispatchers.Main).launch{
		showProgressDialog(null)   //UI：显示进度框
        try {
            val user = getUser() //挂起点
      		tv.text = user.name        //更新UI
        }catch (e:Exception){
            FLog.d("挂起异常${e.message}")
        }
        dismissProgressDialog()    //UI：隐藏进度框
	}
}
```

### 1.6 withContext()
上面通过3步我们自定义了一个挂起函数：
1. 函数使用suspend修饰 
2. 调用协程库提供的suspendCoroutine()或(强制推荐)suspendCancellableCoroutine()挂起函数实现真正、完全的挂起 
3. 开启子线程执行函数体，最后通过Continuation续体对象返回函数结果恢复协程执行

> 步骤是非常清晰，但是代码量和代码清洁度不容乐观，有没有更方便的方式自定义挂起函数呢？
> 协程库提供了withContext()函数，严格说起来它并不是用来自定义挂起函数的，而通常用于线程切换，
> 只是它恰好能实现挂起和异步这两个要素，并且还接受一个函数block作为参数：

```kotlin
suspend fun getUser(): User = withContext(Dispatchers.IO) {
    Thread.sleep(3000)
    when(Random.nextInt(10)%2){ //10以内随机数如果是偶数返回成功
        0->User("bh")
        else-> throw Exception("模拟异常")
    }
}
```


### 1.7 withContext()和suspendCancellableCoroutine()怎么选？
```kotlin
//1. withContext()
public suspend fun <T> withContext(
    context: CoroutineContext,
    block: suspend CoroutineScope.() -> T
): T {
	
    return suspendCoroutineUninterceptedOrReturn sc@ { uCont ->
		
    }
}

//2. suspendCancellableCoroutine()
public suspend inline fun <T> suspendCancellableCoroutine(
    crossinline block: (CancellableContinuation<T>) -> Unit
): T =
    suspendCoroutineUninterceptedOrReturn { uCont ->
        
    }
//都是通过suspendCoroutineUninterceptedOrReturn()实现的
public suspend inline fun <T> suspendCoroutineUninterceptedOrReturn(crossinline block: (Continuation<T>) -> Any?): T {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    
}
```
> withContext()和suspendCancellableCoroutine()函数都是协程库提供的顶层挂起函数，
> 发现他们都调用了suspendCoroutineUninterceptedOrReturn()来捕获协程的续体对象，
> 并且都接受一个函数类型作为参数，这就为自定义挂起函数提供了可行性。但是究竟该选用哪个函数呢？
> withContext()使用更简洁所以都用它就完事了？


> withContext()可接受`协程上下文作为参数`，这样我们可以传入`Dispatchers调度器`自动切换挂起函数执行的线程；
> 而suspendCancellableCoroutine()并不`具备切换线程的能力`，通常需要我们`手动创建线程`。
> 所以当`函数体已经具备异步能力的就选suspendCancellableCoroutine()`，而`不具备异步能力(需要手动切线程)的就选withContext()`


> 需要注意的是，如果使用withContext()则直接通过return返回计算结果或者通过throw抛异常来返回错误从而恢复协程执行；
> 而用suspendCancellableCoroutine()的时候则通过续体continuation的resume系列方法来实现协程恢复



## 总结
协程构建器：

1. runBlocking{}
2. launch{}
3. async{}
4. withContext(Dispatchers.xx){}
5. coroutineScope{}
![image-20220311101024292](/Users/bhuang/IdeaProjects/boot-high/kotlin/readme.assets/image-20220311101024292.png)

在不同的场景创建协程应该使用不同作用域对象，而协程核心库中只有GlobalScope和ContextScope两个子类可以使用：
1. 我只想在main函数中做个测试，那就用runBlocking{}或者GlobalScope.launch{}； 
2. 我想在UI线程中开启协程，那就用MainScope().launch {} 
3. 其他场景中那就用CoroutineScope(context).launch {}，想在哪个线程执行、协程名字是什么…通过传递上下文参数都搞定，**这种方式通吃一切场景**