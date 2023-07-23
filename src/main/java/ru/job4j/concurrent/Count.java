package ru.job4j.concurrent;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class Count {
    @GuardedBy("this")
    private int value;

    public synchronized void increment() {
        this.value++;
    }

<<<<<<< HEAD
<<<<<<< HEAD
    public synchronized int get() {
        return this.value;
    }
}
=======
=======
>>>>>>> origin/master
    public int get() {
        return this.value;
    }
}
<<<<<<< HEAD
>>>>>>> 9a118ef... 2. JCIP. Настройка библиотеки [#268575]
=======
>>>>>>> origin/master
