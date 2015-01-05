package com.igitras.codegen.axon;

import com.igitras.codegen.common.utils.StringUtils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by mason on 12/26/14.
 */
public class Main {
    private static final String SUFFIX = ".java";

    public static void main(String[] args) {
        //        mergeSource();
        try {
            resolveCompileError();
        } catch (IOException e) {
        }
        resolveDependencies();
    }

    public static String MERGE_TARGET = "/Volumes/BACKUP/Mac/SCM/CodeGen/axon-generator/target/src";
    public static String MERGE_SOURCE = "/Volumes/BACKUP/Mac/SCM/intellij-community";
    public static String RESOLVE_TARGET = "/Volumes/BACKUP/Mac/SCM/IntellijCodeFormater/src/main/java";

    protected static void mergeSource() {

        File file = new File(MERGE_SOURCE);
        File target = new File(MERGE_TARGET);
        if (!target.exists()) {
            target.mkdirs();
        }

        Set<File> files = resolveSubFolder(file);
        for (File file1 : files) {
            copyDirectory(file1);
        }
    }

    protected static void copyDirectory(File from) {
        try {
            String fileName = from.getCanonicalPath();
            Collection<File> files = FileUtils.listFiles(from, new String[]{"java"}, true);
            for (File file : files) {
                String canonicalPath = file.getCanonicalPath();
                String toPath = canonicalPath.replace(fileName, MERGE_TARGET);
                FileUtils.copyFile(file, new File(toPath));
            }
        } catch (IOException e) {
        }

    }

    protected static Set<File> resolveSubFolder(File file) {
        if (!file.isDirectory()) {
            return Collections.emptySet();
        }

        if (file.getName().endsWith("src")) {
            return new HashSet<>(Arrays.asList(file));
        }

        Set<File> files = new HashSet<>();

        String[] listFiles = file.list();
        for (String listFile : listFiles) {
            if (listFile.startsWith(".")) {
                continue;
            }
            File child = new File(file.getPath() + File.separator + listFile);
            if (child.isDirectory()) {
                files.addAll(resolveSubFolder(child));
            }
        }
        return files;
    }

    protected static void resolveDependencies() {
        File resolve = new File(RESOLVE_TARGET);
        if (!resolve.exists()) {
            resolve.mkdirs();
        }

        Queue<File> toResolve = new LinkedList<>();
        Set<File> resolved = new HashSet<>();
        Collection<File> source = FileUtils.listFiles(resolve, new String[]{"java"}, true);
        toResolve.addAll(source);

        while (toResolve.size() > 0) {
            File poll = toResolve.poll();
            if (poll == null) {
                continue;
            }

            if (resolved.contains(poll)) {
                continue;
            }

            String[] referredClasses = getReferredClasses(poll);
            for (String referredClass : referredClasses) {
                File file = copyJavaFile(referredClass);
                if (file != null) {
                    toResolve.add(file);
                }
            }
            resolved.add(poll);
        }
    }

    private static File copyJavaFile(String referredClass) {
        String relatedPath = StringUtils.packageToPath(referredClass);
        String fromPath = MERGE_TARGET + File.separator + relatedPath + SUFFIX;
        String toPath = RESOLVE_TARGET + File.separator + relatedPath + SUFFIX;
        File fromFile = new File(fromPath);
        File toFile = null;
        if (fromFile.exists()) {
            try {
                toFile = new File(toPath);
                FileUtils.copyFile(fromFile, toFile);
            } catch (IOException e) {
                e.printStackTrace();
                toFile = null;
            }
        }
        return toFile;
    }

    private static String[] getReferredClasses(File file) {
        Set<String> ref = new HashSet<>();
        try {
            List<String> lines = FileUtils.readLines(file);
            for (String line : lines) {
                String importClass = getImportClass(line);
                if (importClass == null) {
                    continue;
                }
                ref.add(importClass);
            }
        } catch (IOException e) {
        }

        return new ArrayList<>(ref).toArray(new String[ref.size()]);
    }

    private static String getImportClass(String line) {
        if (line.startsWith("import")) {
            String[] split = line.split("[\\s]+", 2);
            String toCheck = split[1].trim();
            toCheck = toCheck.substring(0, toCheck.length() - 1);
            if (toCheck.endsWith("*")) {
                System.out.println(toCheck);
                return null;
            }
            if (toCheck.startsWith("com.intellij") || toCheck.startsWith("org.intellij") ||
                toCheck.startsWith("org.jetbrains") || toCheck.startsWith("com.jetbrains")) {
                return toCheck;
            } else if (toCheck.startsWith("static")) {
                toCheck = toCheck.split("[\\s]+", 2)[1];
                return toCheck.substring(0, toCheck.lastIndexOf('.'));
            }
        }
        return null;
    }

    private static void resolveCompileError() throws IOException {
        File file = new File("/Volumes/BACKUP/Mac/SCM/CodeGen/axon-generator/src/main/resources/temp.txt");
        List<String> lines = FileUtils.readLines(file);
        List<String> error = new ArrayList<>(10);
        List<List<String>> errors = new ArrayList<>();
        int count = -1;
        String fileStr = null;
        for (String line : lines) {
            if (line.endsWith("java")) {
                if (error.size() > 0) {
                    errors.add(error);
                }
                processErrors(errors, fileStr);
                fileStr = line.trim();
                errors.clear();
                continue;
            }
            if (line.startsWith("Error")) {
                if (error.size() > 0) {
                    errors.add(error);
                }
                error = new ArrayList<>();
                count = 0;
                error.add(line);
                count++;
                continue;
            }

            if (line.startsWith("Warning") || line.startsWith("Information")) {
                if (error.size() > 0) {
                    errors.add(error);
                }
                error = new ArrayList<>();
                count = -1;
                continue;
            }

            if (count < 0) {
                continue;
            }

            error.add(line);
            count++;
        }
    }

    private static void processErrors(List<List<String>> errors, String file) {
        for (List<String> error : errors) {
            if (error != null && error.size() >= 2) {
                if (error.get(1).indexOf("class") > 0) {
                    processError(error, file);
                }
            }
        }
    }

    /*
      Error:(142, 43) java: cannot find symbol
      symbol:   class Segment
      location: class com.intellij.openapi.util.TextRange
     */
    private static void processError(List<String> error, String file) {
        String clazzName = error.get(1);
        clazzName = clazzName.trim();
        clazzName = clazzName.substring(clazzName.lastIndexOf(' ') + 1);
        //        String errorFile = error[2];
        //        errorFile = errorFile.trim();
        //        errorFile = errorFile.substring(errorFile.lastIndexOf(' ') + 1);
        //        if(errorFile.indexOf('<') > 0){
        //            errorFile = errorFile.substring(0, errorFile.indexOf('<'));
        //        }
        //        errorFile = StringUtils.packageToPath(errorFile);
        File sourceFile = new File(file);
        try {
            List<String> lines = FileUtils.readLines(sourceFile);
            String packageName = null;
            for (String line : lines) {
                if (line.startsWith("package")) {
                    packageName = line.trim();
                    packageName = packageName.split("[\\s]", 2)[1].trim();
                    packageName = packageName.substring(0, packageName.length() - 1);
                } else if (line.startsWith("import")) {
                    String[] split = line.split("[\\s]+", 2);
                    String toCheck = split[1].trim();
                    toCheck = toCheck.substring(0, toCheck.length() - 1);
                    if (!toCheck.endsWith("*")) {
                        continue;
                    }

                    if (toCheck.startsWith("static")) {
                        continue;
                    }

                    packageName = toCheck.substring(0, toCheck.lastIndexOf('.'));

                }

                if (packageName != null) {
                    resolvePackage(packageName, clazzName);
                    packageName = null;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void resolvePackage(String packageName, String clazzName) {
        String relatedPath = packageName + "." + clazzName;
        copyJavaFile(relatedPath);
    }
}
