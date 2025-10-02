/**
 * هذا الكلاس يمثل "العُقدة" (Node) التي هي الوحدة الأساسية في بناء المكدس.
 * نستخدم Generics <T> لجعله مرنًا وقادرًا على تخزين أي نوع من البيانات (نصوص، أرقام، إلخ).
 */
class Node<T> {
    // T data: هو المتغير الذي يخزن القيمة الفعلية للعقدة
    T data;
    
    // Node<T> next: هو مؤشر (reference) يشير إلى العقدة التالية في السلسلة
    Node<T> next;

    /**
     * هذا هو المُنشئ (Constructor) الخاص بكلاس العقدة.
     * وظيفته هي إنشاء عقدة جديدة وتخزين القيمة بداخلها.
     * @param data القيمة التي سيتم تخزينها في العقدة.
     */
    public Node(T data) {
        // "this.data" تشير إلى المتغير data الخاص بالكائن الحالي
        this.data = data;
        // عند إنشاء عقدة جديدة، فإنها مبدئيًا لا تشير إلى أي شيء، لذا نجعل المؤشر "next" يساوي null
        this.next = null;
    }
}

/**
 * هذا الكلاس يمثل هيكل بيانات "المكدس" (Stack).
 * تم تنفيذه باستخدام "قائمة مترابطة" (Linked List) مكونة من العُقد (Nodes).
 */
class Stack<T> {
    // "top" هو مؤشر خاص يشير دائمًا إلى العقدة الموجودة في قمة المكدس
    private Node<T> top;

    /**
     * دالة للتحقق مما إذا كان المكدس فارغًا أم لا.
     * @return ترجع "true" إذا كان فارغًا، و "false" إذا كان يحتوي على عناصر.
     */
    public boolean isEmpty() {
        // يكون المكدس فارغًا إذا كان مؤشر القمة "top" لا يشير إلى أي شيء (أي يساوي null)
        return top == null;
    }

    /**
     * دالة لإضافة عنصر جديد إلى قمة المكدس (عملية Push).
     * @param data القيمة التي سيتم إضافتها.
     */
    public void push(T data) {
        // 1. ننشئ عقدة جديدة تحتوي على البيانات المراد إضافتها
        Node<T> newNode = new Node<>(data);
        // 2. نجعل العقدة الجديدة تشير إلى ما كان سابقًا في قمة المكدس (top القديم)
        newNode.next = top;
        // 3. نحدّث مؤشر القمة "top" ليصبح هو العقدة الجديدة
        top = newNode;
    }

    /**
     * دالة لحذف العنصر الموجود في قمة المكدس وإرجاع قيمته (عملية Pop).
     * @return القيمة التي تم حذفها.
     * @throws RuntimeException إذا كان المكدس فارغًا عند محاولة الحذف.
     */
    public T pop() {
        // أولاً، نتأكد أن المكدس ليس فارغًا لتجنب الأخطاء
        if (isEmpty()) {
            throw new RuntimeException("خطأ: المكدس فارغ!");
        }
        // 1. نحفظ قيمة العنصر العلوي في متغير مؤقت قبل حذفه
        T value = top.data;
        // 2. نحرّك مؤشر القمة "top" ليشير إلى العقدة التالية (وهذا يحذف العقدة العلوية فعليًا)
        top = top.next;
        // 3. نرجع القيمة التي حفظناها
        return value;
    }

    /**
     * دالة لإرجاع قيمة العنصر العلوي "بدون حذفه" (عملية Peek).
     * @return قيمة العنصر الموجود في قمة المكدس.
     * @throws RuntimeException إذا كان المكدس فارغًا.
     */
    public T peek() {
        // نتأكد أن المكدس ليس فارغًا
        if (isEmpty()) {
            throw new RuntimeException("خطأ: المكدس فارغ!");
        }
        // ببساطة، نرجع قيمة البيانات الموجودة في العقدة التي يشير إليها "top"
        return top.data;
    }

    /**
     * هذه الدالة تساعد على طباعة محتويات المكدس بشكل واضح وسهل القراءة.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<T> current = top; // نبدأ من القمة
        sb.append("Top -> ");
        // نمر على كل العقد من القمة حتى النهاية
        while (current != null) {
            sb.append(current.data).append(" -> ");
            current = current.next; // ننتقل إلى العقدة التالية
        }
        sb.append("null"); // في النهاية نضيف null للإشارة إلى نهاية المكدس
        return sb.toString();
    }
}

// --- مثال توضيحي لكيفية استخدام الكلاس ---
public class StackExample {
    public static void main(String[] args) {
        // ننشئ كائنًا جديدًا من المكدس ليخزن أرقامًا صحيحة (Integer)
        Stack<Integer> stack = new Stack<>();

        // نقوم بإضافة 3 عناصر باستخدام دالة push
        stack.push(10);
        stack.push(20);
        stack.push(30); // 30 هو آخر عنصر تمت إضافته، لذا هو في القمة
        System.out.println("المكدس بعد إضافة 10, 20, 30:");
        System.out.println(stack); // سيطبع: Top -> 30 -> 20 -> 10 -> null

        // نلقي نظرة على العنصر العلوي باستخدام peek (بدون حذفه)
        System.out.println("\nالعنصر في القمة (peek): " + stack.peek()); // سيطبع 30

        // نحذف عنصرين باستخدام pop
        System.out.println("\nالعنصر المحذوف (pop): " + stack.pop()); // سيحذف ويرجع 30
        System.out.println("العنصر المحذوف (pop): " + stack.pop());   // سيحذف ويرجع 20
        System.out.println("المكدس بعد حذف عنصرين:");
        System.out.println(stack); // سيطبع: Top -> 10 -> null

        // نتحقق إذا أصبح المكدس فارغًا
        System.out.println("\nهل المكدس فارغ؟ " + stack.isEmpty()); // سيطبع false
    }
}
