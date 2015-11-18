package cn.rpgmc.mobs.utils.ParticleEffect;

/*     */ 
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;

import org.bukkit.Bukkit;


/*     */ 
/*     */ public final class ReflectionUtils
/*     */ {
/*     */   public static Constructor<?> getConstructor(Class<?> clazz, Class<?>[] parameterTypes)
/*     */     throws NoSuchMethodException
/*     */   {
/*  44 */     Class[] primitiveTypes = DataType.getPrimitive(parameterTypes);
/*  45 */     for (Constructor constructor : clazz.getConstructors())
/*  46 */       if (DataType.compare(DataType.getPrimitive(constructor.getParameterTypes()), primitiveTypes))
/*     */       {
/*  49 */         return constructor;
/*     */       }
/*  51 */     throw new NoSuchMethodException("There is no such constructor in this class with the specified parameter types");
/*     */   }
/*     */ 
/*     */   public static Constructor<?> getConstructor(String className, PackageType packageType, Class<?>[] parameterTypes)
/*     */     throws NoSuchMethodException, ClassNotFoundException
/*     */   {
/*  67 */     return getConstructor(packageType.getClass(className), parameterTypes);
/*     */   }
/*     */ 
/*     */   public static Object instantiateObject(Class<?> clazz, Object[] arguments)
/*     */     throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException
/*     */   {
/*  83 */     return getConstructor(clazz, DataType.getPrimitive(arguments)).newInstance(arguments);
/*     */   }
/*     */ 
/*     */   public static Object instantiateObject(String className, PackageType packageType, Object[] arguments)
/*     */     throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException
/*     */   {
/* 103 */     return instantiateObject(packageType.getClass(className), arguments);
/*     */   }
/*     */ 
/*     */   public static Method getMethod(Class<?> clazz, String methodName, Class<?>[] parameterTypes)
/*     */     throws NoSuchMethodException
/*     */   {
/* 118 */     Class[] primitiveTypes = DataType.getPrimitive(parameterTypes);
/* 119 */     for (Method method : clazz.getMethods())
/* 120 */       if ((method.getName().equals(methodName)) && (DataType.compare(DataType.getPrimitive(method.getParameterTypes()), primitiveTypes)))
/*     */       {
/* 123 */         return method;
/*     */       }
/* 125 */     throw new NoSuchMethodException("There is no such method in this class with the specified name and parameter types");
/*     */   }
/*     */ 
/*     */   public static Method getMethod(String className, PackageType packageType, String methodName, Class<?>[] parameterTypes)
/*     */     throws NoSuchMethodException, ClassNotFoundException
/*     */   {
/* 142 */     return getMethod(packageType.getClass(className), methodName, parameterTypes);
/*     */   }
/*     */ 
/*     */   public static Object invokeMethod(Object instance, String methodName, Object[] arguments)
/*     */     throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException
/*     */   {
/* 160 */     return getMethod(instance.getClass(), methodName, DataType.getPrimitive(arguments)).invoke(instance, arguments);
/*     */   }
/*     */ 
/*     */   public static Object invokeMethod(Object instance, Class<?> clazz, String methodName, Object[] arguments)
/*     */     throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException
/*     */   {
/* 179 */     return getMethod(clazz, methodName, DataType.getPrimitive(arguments)).invoke(instance, arguments);
/*     */   }
/*     */ 
/*     */   public static Object invokeMethod(Object instance, String className, PackageType packageType, String methodName, Object[] arguments)
/*     */     throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException
/*     */   {
/* 200 */     return invokeMethod(instance, packageType.getClass(className), methodName, arguments);
/*     */   }
/*     */ 
/*     */   public static Field getField(Class<?> clazz, boolean declared, String fieldName)
/*     */     throws NoSuchFieldException, SecurityException
/*     */   {
/* 214 */     Field field = declared ? clazz.getDeclaredField(fieldName) : clazz.getField(fieldName);
/* 215 */     field.setAccessible(true);
/* 216 */     return field;
/*     */   }
/*     */ 
/*     */   public static Field getField(String className, PackageType packageType, boolean declared, String fieldName)
/*     */     throws NoSuchFieldException, SecurityException, ClassNotFoundException
/*     */   {
/* 233 */     return getField(packageType.getClass(className), declared, fieldName);
/*     */   }
/*     */ 
/*     */   public static Object getValue(Object instance, Class<?> clazz, boolean declared, String fieldName)
/*     */     throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException
/*     */   {
/* 251 */     return getField(clazz, declared, fieldName).get(instance);
/*     */   }
/*     */ 
/*     */   public static Object getValue(Object instance, String className, PackageType packageType, boolean declared, String fieldName)
/*     */     throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, ClassNotFoundException
/*     */   {
/* 271 */     return getValue(instance, packageType.getClass(className), declared, fieldName);
/*     */   }
/*     */ 
/*     */   public static Object getValue(Object instance, boolean declared, String fieldName)
/*     */     throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException
/*     */   {
/* 288 */     return getValue(instance, instance.getClass(), declared, fieldName);
/*     */   }
/*     */ 
/*     */   public static void setValue(Object instance, Class<?> clazz, boolean declared, String fieldName, Object value)
/*     */     throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException
/*     */   {
/* 306 */     getField(clazz, declared, fieldName).set(instance, value);
/*     */   }
/*     */ 
/*     */   public static void setValue(Object instance, String className, PackageType packageType, boolean declared, String fieldName, Object value)
/*     */     throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, ClassNotFoundException
/*     */   {
/* 326 */     setValue(instance, packageType.getClass(className), declared, fieldName, value);
/*     */   }
/*     */ 
/*     */   public static void setValue(Object instance, boolean declared, String fieldName, Object value)
/*     */     throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException
/*     */   {
/* 343 */     setValue(instance, instance.getClass(), declared, fieldName, value);
/*     */   }
/*     */ 
/*     */   public static enum DataType
/*     */   {
/* 442 */     BYTE(Byte.TYPE, Byte.class), 
/* 443 */     SHORT(Short.TYPE, Short.class), 
/* 444 */     INTEGER(Integer.TYPE, Integer.class), 
/* 445 */     LONG(Long.TYPE, Long.class), 
/* 446 */     CHARACTER(Character.TYPE, Character.class), 
/* 447 */     FLOAT(Float.TYPE, Float.class), 
/* 448 */     DOUBLE(Double.TYPE, Double.class), 
/* 449 */     BOOLEAN(Boolean.TYPE, Boolean.class);
/*     */ 
/*     */     private static final Map<Class<?>, DataType> CLASS_MAP;
/*     */     private final Class<?> primitive;
/*     */     private final Class<?> reference;
/*     */ 
/* 451 */     static { CLASS_MAP = new HashMap();
/*     */ 
/* 457 */       for (DataType type : values()) {
/* 458 */         CLASS_MAP.put(type.primitive, type);
/* 459 */         CLASS_MAP.put(type.reference, type);
/*     */       }
/*     */     }
/*     */ 
/*     */     private DataType(Class<?> primitive, Class<?> reference)
/*     */     {
/* 470 */       this.primitive = primitive;
/* 471 */       this.reference = reference;
/*     */     }
/*     */ 
/*     */     public Class<?> getPrimitive()
/*     */     {
/* 480 */       return this.primitive;
/*     */     }
/*     */ 
/*     */     public Class<?> getReference()
/*     */     {
/* 489 */       return this.reference;
/*     */     }
/*     */ 
/*     */     public static DataType fromClass(Class<?> clazz)
/*     */     {
/* 499 */       return (DataType)CLASS_MAP.get(clazz);
/*     */     }
/*     */ 
/*     */     public static Class<?> getPrimitive(Class<?> clazz)
/*     */     {
/* 509 */       DataType type = fromClass(clazz);
/* 510 */       return type == null ? clazz : type.getPrimitive();
/*     */     }
/*     */ 
/*     */     public static Class<?> getReference(Class<?> clazz)
/*     */     {
/* 520 */       DataType type = fromClass(clazz);
/* 521 */       return type == null ? clazz : type.getReference();
/*     */     }
/*     */ 
/*     */     public static Class<?>[] getPrimitive(Class<?>[] classes)
/*     */     {
/* 531 */       int length = classes == null ? 0 : classes.length;
/* 532 */       Class[] types = new Class[length];
/* 533 */       for (int index = 0; index < length; index++) {
/* 534 */         types[index] = getPrimitive(classes[index]);
/*     */       }
/* 536 */       return types;
/*     */     }
/*     */ 
/*     */     public static Class<?>[] getReference(Class<?>[] classes)
/*     */     {
/* 546 */       int length = classes == null ? 0 : classes.length;
/* 547 */       Class[] types = new Class[length];
/* 548 */       for (int index = 0; index < length; index++) {
/* 549 */         types[index] = getReference(classes[index]);
/*     */       }
/* 551 */       return types;
/*     */     }
/*     */ 
/*     */     public static Class<?>[] getPrimitive(Object[] objects)
/*     */     {
/* 561 */       int length = objects == null ? 0 : objects.length;
/* 562 */       Class[] types = new Class[length];
/* 563 */       for (int index = 0; index < length; index++) {
/* 564 */         types[index] = getPrimitive(objects[index].getClass());
/*     */       }
/* 566 */       return types;
/*     */     }
/*     */ 
/*     */     public static Class<?>[] getReference(Object[] objects)
/*     */     {
/* 576 */       int length = objects == null ? 0 : objects.length;
/* 577 */       Class[] types = new Class[length];
/* 578 */       for (int index = 0; index < length; index++) {
/* 579 */         types[index] = getReference(objects[index].getClass());
/*     */       }
/* 581 */       return types;
/*     */     }
/*     */ 
/*     */     public static boolean compare(Class<?>[] primary, Class<?>[] secondary)
/*     */     {
/* 592 */       if ((primary == null) || (secondary == null) || (primary.length != secondary.length)) {
/* 593 */         return false;
/*     */       }
/* 595 */       for (int index = 0; index < primary.length; index++) {
/* 596 */         Class primaryClass = primary[index];
/* 597 */         Class secondaryClass = secondary[index];
/* 598 */         if ((!primaryClass.equals(secondaryClass)) && (!primaryClass.isAssignableFrom(secondaryClass)))
/*     */         {
/* 601 */           return false;
/*     */         }
/*     */       }
/* 603 */       return true;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static enum PackageType
/*     */   {
/* 355 */     MINECRAFT_SERVER("net.minecraft.server." + getServerVersion()), 
/* 356 */     CRAFTBUKKIT("org.bukkit.craftbukkit." + getServerVersion()), 
/* 357 */     CRAFTBUKKIT_BLOCK(CRAFTBUKKIT, "block"), 
/* 358 */     CRAFTBUKKIT_CHUNKIO(CRAFTBUKKIT, "chunkio"), 
/* 359 */     CRAFTBUKKIT_COMMAND(CRAFTBUKKIT, "command"), 
/* 360 */     CRAFTBUKKIT_CONVERSATIONS(CRAFTBUKKIT, "conversations"), 
/* 361 */     CRAFTBUKKIT_ENCHANTMENS(CRAFTBUKKIT, "enchantments"), 
/* 362 */     CRAFTBUKKIT_ENTITY(CRAFTBUKKIT, "entity"), 
/* 363 */     CRAFTBUKKIT_EVENT(CRAFTBUKKIT, "event"), 
/* 364 */     CRAFTBUKKIT_GENERATOR(CRAFTBUKKIT, "generator"), 
/* 365 */     CRAFTBUKKIT_HELP(CRAFTBUKKIT, "help"), 
/* 366 */     CRAFTBUKKIT_INVENTORY(CRAFTBUKKIT, "inventory"), 
/* 367 */     CRAFTBUKKIT_MAP(CRAFTBUKKIT, "map"), 
/* 368 */     CRAFTBUKKIT_METADATA(CRAFTBUKKIT, "metadata"), 
/* 369 */     CRAFTBUKKIT_POTION(CRAFTBUKKIT, "potion"), 
/* 370 */     CRAFTBUKKIT_PROJECTILES(CRAFTBUKKIT, "projectiles"), 
/* 371 */     CRAFTBUKKIT_SCHEDULER(CRAFTBUKKIT, "scheduler"), 
/* 372 */     CRAFTBUKKIT_SCOREBOARD(CRAFTBUKKIT, "scoreboard"), 
/* 373 */     CRAFTBUKKIT_UPDATER(CRAFTBUKKIT, "updater"), 
/* 374 */     CRAFTBUKKIT_UTIL(CRAFTBUKKIT, "util");
/*     */ 
/*     */     private final String path;
/*     */ 
/*     */     private PackageType(String path)
/*     */     {
/* 384 */       this.path = path;
/*     */     }
/*     */ 
/*     */     private PackageType(PackageType parent, String path)
/*     */     {
/* 394 */       this(parent + "." + path);
/*     */     }
/*     */ 
/*     */     public String getPath()
/*     */     {
/* 403 */       return this.path;
/*     */     }
/*     */ 
/*     */     public Class<?> getClass(String className)
/*     */       throws ClassNotFoundException
/*     */     {
/* 414 */       return Class.forName(this + "." + className);
/*     */     }
/*     */ 
/*     */     public String toString()
/*     */     {
/* 420 */       return this.path;
/*     */     }
/*     */ 
/*     */     public static String getServerVersion()
/*     */     {
/* 429 */       return Bukkit.getServer().getClass().getPackage().getName().substring(23);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\11608\Downloads\FunnyEffect.jar
 * Qualified Name:     fr.neilime.utils.ReflectionUtils
 * JD-Core Version:    0.6.2
 */