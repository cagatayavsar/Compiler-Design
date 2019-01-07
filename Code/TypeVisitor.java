
public class TypeVisitor implements Visitor {
  SymTable t;
  public static void main(String args[]) throws ParseException {
     EqList el = new Parser(System.in).Parser();
     int a = new TypeVisitor(new SymTable()).visit(el);
     System.out.println(a == 0 ? "Success" : "Fail");
  }

  public TypeVisitor(SymTable t) {
    this.t = t;
  }
  public int visit(EqList e) {
    int a = e.e.accept(this);
    int b = 0;
    if (e.el != null)
      b = e.el.accept(this);
    return a + b;
  }

  public int visit(FnLExp e) {
    if (!t.contains(e.id, 1))
      t.put(e.id, 1);
    if (e.e instanceof IdExp)
      t.put(((IdExp)e.e).id, 0);
    return 0;
  }

  public int visit(Eq e) {
    t.beginScope();
    int a = e.f.accept(this);
    int b = e.e.accept(this);
    t.endScope();
    return a + b;
  }

  public int visit(Exp e) {
    return e.accept(this);
  }

  public int visit(IdExp e) {
    if (!t.contains(e.id, 0)) {
      System.out.println("parameter " + e.id + " not defined!");
      return 1;
    }
    return 0;
  }

  public int visit(NumExp e) {
    return 0;
  }

  public int visit(PlusExp e) {
    int a = e.e1.accept(this);
    int b = e.e2.accept(this);
    return a + b;
  }

  public int visit(MinusExp e) {
    int a = e.e1.accept(this);
    int b = e.e2.accept(this);
    return a + b;
  }

  public int visit(TimesExp e) {
    int a = e.e1.accept(this);
    int b = e.e2.accept(this);
    return a + b;
}

  public int visit(DivideExp e) {
    int a = e.e1.accept(this);
    int b = e.e2.accept(this);
    return a + b;
  }

  public int visit(ModExp e) {
    int a = e.e1.accept(this);
    int b = e.e2.accept(this);
    return a + b;
  }

  public int visit(PowerExp e) {
    int a = e.e1.accept(this);
    int b = e.e2.accept(this);
    return a + b;
  }

  public int visit(FnRExp e) {
    if (!t.contains(e.id, 1)) {
      System.out.println("function " + e.id + " not defined!");
      return 1;
    }
    int a = e.e.accept(this);
    return a;
  }
}

