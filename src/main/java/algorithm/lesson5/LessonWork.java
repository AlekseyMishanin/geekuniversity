package algorithm.lesson5;

public class LessonWork {
    /*
    Последовательность чисел Фибоначчи
    1. Дано натуральное число n. Найти n-ый член последовательности Фибоначчи
    f(1) = 1, f(2) = 1, f(n) = f(n-1) + f(n-2)
    1, 1, 2, 3, 5, 8, 13, 21, ...
    Число золотого сечения (число Бога) = f(n) / f(n-1) = ~1.618
    */

    /*
    2. Дано натуральное число n. Найти n! = [1 * 2 * 3 * 4 ... * (n - 1)] * n = (n - 1)! * n
    f(n) = f(n - 1) * n
    */

    /*
    3. Дано натуральное число n. Найти n-е треугольное число = [1 + 2 + 3 + 4 ... + (n - 1)] + n = (n - 1)! + n
    f(n) = f(n - 1) + n
    */

    /*
    4. Дано натуральное число n. Найти сумму цифр числа n
    f(n) - сумма цифр числа n
    f(n) = f(n / 10) + n % 10;
    */

    /*
    5. Даны два целых не отрицательных числа а и б. Без использования операции * найти произведение чисел а и б.
    а * б = (а + ... + а) б раз
    f(а, б) = f(а, б - 1) + а
    */

    public static void main(String[] args) {
        System.out.println(phibo(6));
        System.out.println(phiboRecursio(6));
        System.out.println(factorial(5));
        System.out.println(factorialRecursio(5));
        System.out.println(triangle(5));
        System.out.println(triangleRecursio(5));
    }

    public static long phibo(int n){
        long current = 1;
        int currentIndex = 1;
        long previous = 0;

        while(currentIndex< n){
            long next = current + previous;
            previous = current;
            current = next;
            currentIndex++;
        }
        return current;
    }

    public static long phiboRecursio(int n){
        if(n<3){
            return 1;
        } else {
          return phiboRecursio(n-1) + phiboRecursio(n-2);
        }
    }

    public static long factorial(int n){
        long product = 1;
        while (n > 0){
            product*=n;
            n--;
        }
        return product;
    }

    public static long factorialRecursio(int n){
        if(n == 1){
            return 1;
        } else {
            return factorialRecursio(n - 1) * n;
        }
    }

    public static long triangle(int n){
        long product = 1;
        while (n > 0){
            product+=n;
            n--;
        }
        return product;
    }

    public static long triangleRecursio(int n){
        if(n == 1){
            return 1;
        } else {
            return factorialRecursio(n - 1) + n;
        }
    }

    public static int digitSum(int n){
        int sum = 0;
        while (n > 0){
            int digit = n % 10;
            sum += digit;
            n /=10;
        }
        return sum;
    }

    public static int digitRecursio(int n){
        if(n<10){
            return n;
        } else {
            return n % 10 + digitRecursio(n / 10);
        }
    }

    public  static int multi(int a, int b){
        int sum = 0;
        if(b > a){
            int temp = a;
            a = b;
            b = temp;
        }
        while(b > 0){
            sum += a;
            b--;
        }
        return sum;
    }

    public  static int multiRecursio(int a, int b){
        if(b==0){
            return 0;
        } else if (b==1){
            return a;
        } else {
            return multiRecursio(a, b - 1) + a;
        }
    }

}
