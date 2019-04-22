import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject

fun printCurrentThread(tag: String) = println("$tag: ${Thread.currentThread().name.substringBefore("-")}")

fun main(args: Array<String>) {
    printCurrentThread("main thread name")

    val subject = BehaviorSubject.create<Unit>()

    subject
        .subscribeOn(Schedulers.computation())
        .subscribe {
            printCurrentThread("subscribe onNext")
        }

    Thread.sleep(100L)

    subject.onNext(Unit)

    Thread.sleep(100L)
}