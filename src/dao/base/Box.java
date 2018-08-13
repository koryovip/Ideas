package dao.base;

import java.util.function.Consumer;

//型Tの値をもつコンテナ
interface Box<T> {
    //保持する値を返す
    T get();

    //値を設定する
    void put(T element);

    //別のコンテナの値を設定する
    void put(Box<? extends T> box);

    //Consumer関数を適用する
    void applyConsumer(Consumer<? super T> function);
}