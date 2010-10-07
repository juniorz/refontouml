package org.eclipse.emf.codegen.ecore.templates.model;

import java.util.*;
import org.eclipse.emf.codegen.ecore.genmodel.*;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.ecore.*;
import org.eclipse.emf.ecore.util.EcoreUtil;

public class ValidatorClass_
{
  protected static String nl;
  public static synchronized ValidatorClass_ create(String lineSeparator)
  {
    nl = lineSeparator;
    ValidatorClass_ result = new ValidatorClass_();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = "/**";
  protected final String TEXT_3 = NL + " * ";
  protected final String TEXT_4 = NL + " * <copyright>" + NL + " * </copyright>";
  protected final String TEXT_5 = NL + " *" + NL + " * ";
  protected final String TEXT_6 = "Id";
  protected final String TEXT_7 = NL + " */" + NL + "package ";
  protected final String TEXT_8 = ";" + NL;
  protected final String TEXT_9 = NL + NL + "/**" + NL + " * <!-- begin-user-doc -->" + NL + " * The <b>Validator</b> for the model." + NL + " * <!-- end-user-doc -->" + NL + " * @see ";
  protected final String TEXT_10 = NL + " * @generated" + NL + " */" + NL + "public class ";
  protected final String TEXT_11 = " extends ";
  protected final String TEXT_12 = NL + "{";
  protected final String TEXT_13 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static final ";
  protected final String TEXT_14 = " copyright = \"";
  protected final String TEXT_15 = "\";";
  protected final String TEXT_16 = NL;
  protected final String TEXT_17 = NL + "\t/**" + NL + "\t * The cached model package" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static final ";
  protected final String TEXT_18 = " INSTANCE = new ";
  protected final String TEXT_19 = "();" + NL + "" + NL + "\t/**" + NL + "\t * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see org.eclipse.emf.common.util.Diagnostic#getSource()" + NL + "\t * @see org.eclipse.emf.common.util.Diagnostic#getCode()" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static final String DIAGNOSTIC_SOURCE = \"";
  protected final String TEXT_20 = "\";";
  protected final String TEXT_21 = NL;
  protected final String TEXT_22 = NL + "\t/**" + NL + "\t * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint '";
  protected final String TEXT_23 = "' of '";
  protected final String TEXT_24 = "'." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static final int ";
  protected final String TEXT_25 = " = ";
  protected final String TEXT_26 = ";" + NL;
  protected final String TEXT_27 = NL + "\t/**" + NL + "\t * A constant with a fixed name that can be used as the base value for additional hand written constants." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate static final int GENERATED_DIAGNOSTIC_CODE_COUNT = ";
  protected final String TEXT_28 = ";" + NL + "" + NL + "\t/**" + NL + "\t * A constant with a fixed name that can be used as the base value for additional hand written constants in a derived class." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected static final int DIAGNOSTIC_CODE_COUNT = GENERATED_DIAGNOSTIC_CODE_COUNT;" + NL;
  protected final String TEXT_29 = NL + "\t/**" + NL + "\t * The cached base package validator." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected ";
  protected final String TEXT_30 = " ";
  protected final String TEXT_31 = "Validator;" + NL;
  protected final String TEXT_32 = NL + "\t/**" + NL + "\t * The parsed OCL expression for the definition of the '<em>";
  protected final String TEXT_33 = "</em>' invariant constraint." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate static ";
  protected final String TEXT_34 = " ";
  protected final String TEXT_35 = "_";
  protected final String TEXT_36 = "InvOCL;";
  protected final String TEXT_37 = NL + "\tprivate static final String OCL_ANNOTATION_SOURCE = \"";
  protected final String TEXT_38 = "\";";
  protected final String TEXT_39 = NL + "\t" + NL + "\tprivate static final ";
  protected final String TEXT_40 = " OCL_ENV = ";
  protected final String TEXT_41 = ".newInstance();";
  protected final String TEXT_42 = NL + "\t/**" + NL + "\t * Creates an instance of the switch." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_43 = "()" + NL + "\t{" + NL + "\t\tsuper();";
  protected final String TEXT_44 = NL + "\t\t";
  protected final String TEXT_45 = "Validator = ";
  protected final String TEXT_46 = ".INSTANCE;";
  protected final String TEXT_47 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * Returns the package of this validator switch." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_48 = NL + "\t@Override";
  protected final String TEXT_49 = NL + "\tprotected EPackage getEPackage()" + NL + "\t{" + NL + "\t  return ";
  protected final String TEXT_50 = ".eINSTANCE;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * Calls <code>validateXXX</code> for the corresonding classifier of the model." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_51 = NL + "\t@Override";
  protected final String TEXT_52 = NL + "\tprotected boolean validate(int classifierID, Object value, DiagnosticChain diagnostics, ";
  protected final String TEXT_53 = " context)" + NL + "\t{" + NL + "\t\tswitch (classifierID)" + NL + "\t\t{";
  protected final String TEXT_54 = NL + "\t\t\tcase ";
  protected final String TEXT_55 = ".";
  protected final String TEXT_56 = ":";
  protected final String TEXT_57 = NL + "\t\t\t\treturn validate";
  protected final String TEXT_58 = "(((";
  protected final String TEXT_59 = ")value).";
  protected final String TEXT_60 = "(), diagnostics, context);";
  protected final String TEXT_61 = NL + "\t\t\t\treturn validate";
  protected final String TEXT_62 = "(value, diagnostics, context);";
  protected final String TEXT_63 = NL + "\t\t\t\treturn validate";
  protected final String TEXT_64 = "((";
  protected final String TEXT_65 = ")value, diagnostics, context);";
  protected final String TEXT_66 = NL + "\t\t\t\treturn validate";
  protected final String TEXT_67 = "((";
  protected final String TEXT_68 = ")value, diagnostics, context);";
  protected final String TEXT_69 = NL + "\t\t\tdefault: " + NL + "\t\t\t\treturn true;" + NL + "\t\t}" + NL + "\t}" + NL;
  protected final String TEXT_70 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic boolean validate";
  protected final String TEXT_71 = "(";
  protected final String TEXT_72 = " ";
  protected final String TEXT_73 = ", DiagnosticChain ";
  protected final String TEXT_74 = ", ";
  protected final String TEXT_75 = " ";
  protected final String TEXT_76 = ")" + NL + "\t{";
  protected final String TEXT_77 = NL + "\t\treturn true;";
  protected final String TEXT_78 = NL + "\t\treturn validate_EveryDefaultConstraint(";
  protected final String TEXT_79 = "(";
  protected final String TEXT_80 = ")";
  protected final String TEXT_81 = ", ";
  protected final String TEXT_82 = ", ";
  protected final String TEXT_83 = ");";
  protected final String TEXT_84 = NL + "\t\tboolean ";
  protected final String TEXT_85 = " = ";
  protected final String TEXT_86 = "validate";
  protected final String TEXT_87 = "_";
  protected final String TEXT_88 = "(";
  protected final String TEXT_89 = ", ";
  protected final String TEXT_90 = ", ";
  protected final String TEXT_91 = ");";
  protected final String TEXT_92 = NL + "\t\tif (";
  protected final String TEXT_93 = " || ";
  protected final String TEXT_94 = " != null) result &= ";
  protected final String TEXT_95 = "validate";
  protected final String TEXT_96 = "_";
  protected final String TEXT_97 = "(";
  protected final String TEXT_98 = ", ";
  protected final String TEXT_99 = ", ";
  protected final String TEXT_100 = ");";
  protected final String TEXT_101 = NL + "\t\treturn ";
  protected final String TEXT_102 = ";";
  protected final String TEXT_103 = NL + "\t}" + NL;
  protected final String TEXT_104 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @see #validate";
  protected final String TEXT_105 = "_";
  protected final String TEXT_106 = NL + "\t */" + NL + "\tpublic static final ";
  protected final String TEXT_107 = " ";
  protected final String TEXT_108 = "__VALUE = ";
  protected final String TEXT_109 = ";" + NL;
  protected final String TEXT_110 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @see #validate";
  protected final String TEXT_111 = "_";
  protected final String TEXT_112 = NL + "\t */" + NL + "\tpublic static final ";
  protected final String TEXT_113 = " ";
  protected final String TEXT_114 = "__VALUE = ";
  protected final String TEXT_115 = ";" + NL;
  protected final String TEXT_116 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @see #validate";
  protected final String TEXT_117 = "_";
  protected final String TEXT_118 = NL + "\t */" + NL + "\tpublic static final ";
  protected final String TEXT_119 = " ";
  protected final String TEXT_120 = "__UPPER_BOUND = ";
  protected final String TEXT_121 = ";" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @see #validate";
  protected final String TEXT_122 = "_";
  protected final String TEXT_123 = NL + "\t */" + NL + "\tpublic static final ";
  protected final String TEXT_124 = " ";
  protected final String TEXT_125 = "__LOWER_BOUND = ";
  protected final String TEXT_126 = ";" + NL;
  protected final String TEXT_127 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @see #validate";
  protected final String TEXT_128 = "_";
  protected final String TEXT_129 = NL + "\t */" + NL + "\tpublic static final  PatternMatcher [][] ";
  protected final String TEXT_130 = "__VALUES =" + NL + "\t\tnew PatternMatcher [][] " + NL + "\t\t{";
  protected final String TEXT_131 = NL + "\t\t\tnew PatternMatcher [] " + NL + "\t\t\t{";
  protected final String TEXT_132 = NL + "\t\t\t\t";
  protected final String TEXT_133 = ".createPatternMatcher(";
  protected final String TEXT_134 = ")";
  protected final String TEXT_135 = NL + "\t\t\t}";
  protected final String TEXT_136 = NL + "\t\t};" + NL;
  protected final String TEXT_137 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @see #validate";
  protected final String TEXT_138 = "_";
  protected final String TEXT_139 = NL + "\t */" + NL + "\tpublic static final ";
  protected final String TEXT_140 = " ";
  protected final String TEXT_141 = "__VALUES =" + NL + "\t\twrapEnumerationValues" + NL + "\t\t\t(new Object[] " + NL + "\t\t\t {";
  protected final String TEXT_142 = NL + "\t\t\t\t ";
  protected final String TEXT_143 = "new ";
  protected final String TEXT_144 = "(";
  protected final String TEXT_145 = ")";
  protected final String TEXT_146 = NL + "\t\t\t });" + NL;
  protected final String TEXT_147 = NL + "\t/**" + NL + "\t * Validates the ";
  protected final String TEXT_148 = " constraint of '<em>";
  protected final String TEXT_149 = "</em>'." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic boolean validate";
  protected final String TEXT_150 = "_";
  protected final String TEXT_151 = "(";
  protected final String TEXT_152 = " ";
  protected final String TEXT_153 = ", DiagnosticChain ";
  protected final String TEXT_154 = ", ";
  protected final String TEXT_155 = " ";
  protected final String TEXT_156 = ")" + NL + "\t{";
  protected final String TEXT_157 = NL + "\t\tboolean ";
  protected final String TEXT_158 = " = true;" + NL + "\t\tfor (";
  protected final String TEXT_159 = " i = ";
  protected final String TEXT_160 = ".iterator(); i.hasNext() && (result || diagnostics != null); )" + NL + "\t\t{" + NL + "\t\t\tObject item = i.next();";
  protected final String TEXT_161 = NL + "\t\t\tif (";
  protected final String TEXT_162 = ".isInstance(item))" + NL + "\t\t\t{" + NL + "\t\t\t\tresult &= ";
  protected final String TEXT_163 = "validate";
  protected final String TEXT_164 = "(";
  protected final String TEXT_165 = "(";
  protected final String TEXT_166 = "(";
  protected final String TEXT_167 = ")";
  protected final String TEXT_168 = ").";
  protected final String TEXT_169 = "()";
  protected final String TEXT_170 = ", ";
  protected final String TEXT_171 = ", ";
  protected final String TEXT_172 = ");" + NL + "\t\t\t}" + NL + "\t\t\telse";
  protected final String TEXT_173 = NL + "\t\t\tif (!";
  protected final String TEXT_174 = ".isInstance(item))";
  protected final String TEXT_175 = NL + "\t\t\t{" + NL + "\t\t\t\tresult = false;" + NL + "\t\t\t\treportDataValueTypeViolation(";
  protected final String TEXT_176 = ", item, ";
  protected final String TEXT_177 = ", ";
  protected final String TEXT_178 = ");" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\treturn result;";
  protected final String TEXT_179 = NL + "\t\tif (diagnostics != null)" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_180 = " tempDiagnostics = new BasicDiagnostic();";
  protected final String TEXT_181 = NL + "\t\t\tif (";
  protected final String TEXT_182 = "validate";
  protected final String TEXT_183 = "(";
  protected final String TEXT_184 = ", tempDiagnostics, ";
  protected final String TEXT_185 = ")) return true;";
  protected final String TEXT_186 = NL + "\t\t\tif (";
  protected final String TEXT_187 = ".isInstance(";
  protected final String TEXT_188 = "))" + NL + "\t\t\t{" + NL + "\t\t\t\t";
  protected final String TEXT_189 = "if (";
  protected final String TEXT_190 = "validate";
  protected final String TEXT_191 = "(";
  protected final String TEXT_192 = "(";
  protected final String TEXT_193 = "(";
  protected final String TEXT_194 = ")";
  protected final String TEXT_195 = ").";
  protected final String TEXT_196 = "()";
  protected final String TEXT_197 = ", tempDiagnostics, ";
  protected final String TEXT_198 = ")) ";
  protected final String TEXT_199 = "return true;" + NL + "\t\t\t}";
  protected final String TEXT_200 = NL + "\t\t\tfor (";
  protected final String TEXT_201 = " diagnostic : tempDiagnostics.getChildren())" + NL + "\t\t\t{" + NL + "\t\t\t\tdiagnostics.add(diagnostic);" + NL + "\t\t\t}";
  protected final String TEXT_202 = NL + "\t\t\t";
  protected final String TEXT_203 = " children = tempDiagnostics.getChildren();" + NL + "\t\t\tfor (int i = 0; i < children.size(); i++)" + NL + "\t\t\t{" + NL + "\t\t\t\tdiagnostics.add((";
  protected final String TEXT_204 = ")children.get(i));" + NL + "\t\t\t}";
  protected final String TEXT_205 = NL + "\t\t}" + NL + "\t\telse" + NL + "\t\t{";
  protected final String TEXT_206 = NL + "\t\t\tif (";
  protected final String TEXT_207 = "validate";
  protected final String TEXT_208 = "(";
  protected final String TEXT_209 = ", null, ";
  protected final String TEXT_210 = ")) return true;";
  protected final String TEXT_211 = NL + "\t\t\tif (";
  protected final String TEXT_212 = ".isInstance(";
  protected final String TEXT_213 = "))" + NL + "\t\t\t{" + NL + "\t\t\t\t";
  protected final String TEXT_214 = "if (";
  protected final String TEXT_215 = "validate";
  protected final String TEXT_216 = "(";
  protected final String TEXT_217 = "(";
  protected final String TEXT_218 = "(";
  protected final String TEXT_219 = ")";
  protected final String TEXT_220 = ").";
  protected final String TEXT_221 = "()";
  protected final String TEXT_222 = ", null, ";
  protected final String TEXT_223 = ")) ";
  protected final String TEXT_224 = "return true;" + NL + "\t\t\t}";
  protected final String TEXT_225 = NL + "\t\t}" + NL + "\t\treturn false;";
  protected final String TEXT_226 = NL + "\t\treturn validatePattern(";
  protected final String TEXT_227 = ", ";
  protected final String TEXT_228 = "new ";
  protected final String TEXT_229 = "(";
  protected final String TEXT_230 = ")";
  protected final String TEXT_231 = ", ";
  protected final String TEXT_232 = "__VALUES, ";
  protected final String TEXT_233 = ", ";
  protected final String TEXT_234 = ");";
  protected final String TEXT_235 = NL + "\t\t";
  protected final String TEXT_236 = " ";
  protected final String TEXT_237 = " = new ";
  protected final String TEXT_238 = "(";
  protected final String TEXT_239 = ");";
  protected final String TEXT_240 = NL + "\t\tboolean ";
  protected final String TEXT_241 = " = ";
  protected final String TEXT_242 = "__VALUES.contains(";
  protected final String TEXT_243 = ");" + NL + "\t\tif (!";
  protected final String TEXT_244 = " && ";
  protected final String TEXT_245 = " != null) " + NL + "\t\t\treportEnumerationViolation(";
  protected final String TEXT_246 = ", ";
  protected final String TEXT_247 = ", ";
  protected final String TEXT_248 = "__VALUES, ";
  protected final String TEXT_249 = ", ";
  protected final String TEXT_250 = ");" + NL + "\t\treturn ";
  protected final String TEXT_251 = "; ";
  protected final String TEXT_252 = NL + "\t\tboolean ";
  protected final String TEXT_253 = " = ";
  protected final String TEXT_254 = " ";
  protected final String TEXT_255 = " ";
  protected final String TEXT_256 = "__VALUE;" + NL + "\t\tif (!";
  protected final String TEXT_257 = " && ";
  protected final String TEXT_258 = " != null) " + NL + "\t\t\treportMinViolation(";
  protected final String TEXT_259 = ", new ";
  protected final String TEXT_260 = "(";
  protected final String TEXT_261 = "), new ";
  protected final String TEXT_262 = "(";
  protected final String TEXT_263 = "__VALUE), ";
  protected final String TEXT_264 = ", ";
  protected final String TEXT_265 = ", ";
  protected final String TEXT_266 = ");" + NL + "\t\treturn ";
  protected final String TEXT_267 = ";";
  protected final String TEXT_268 = NL + "\t\tint ";
  protected final String TEXT_269 = " = ";
  protected final String TEXT_270 = ".compareCalendar(";
  protected final String TEXT_271 = ", ";
  protected final String TEXT_272 = "__VALUE);" + NL + "\t\tboolean ";
  protected final String TEXT_273 = " = ";
  protected final String TEXT_274 = " == 0 || ";
  protected final String TEXT_275 = " == 1;";
  protected final String TEXT_276 = NL + "\t\tint ";
  protected final String TEXT_277 = " = ";
  protected final String TEXT_278 = ".compareDuration(";
  protected final String TEXT_279 = ", ";
  protected final String TEXT_280 = "__VALUE);" + NL + "\t\tboolean ";
  protected final String TEXT_281 = " = ";
  protected final String TEXT_282 = " == 0 || ";
  protected final String TEXT_283 = " == 1;";
  protected final String TEXT_284 = NL + "\t\tboolean ";
  protected final String TEXT_285 = " = ";
  protected final String TEXT_286 = ".compareTo(";
  protected final String TEXT_287 = "__VALUE) ";
  protected final String TEXT_288 = " 0;";
  protected final String TEXT_289 = NL + "\t\tif (!";
  protected final String TEXT_290 = " && ";
  protected final String TEXT_291 = " != null) " + NL + "\t\t\treportMinViolation(";
  protected final String TEXT_292 = ", ";
  protected final String TEXT_293 = ", ";
  protected final String TEXT_294 = "__VALUE, ";
  protected final String TEXT_295 = ", ";
  protected final String TEXT_296 = ", ";
  protected final String TEXT_297 = ");" + NL + "\t\treturn ";
  protected final String TEXT_298 = "; ";
  protected final String TEXT_299 = NL + "\t\tboolean ";
  protected final String TEXT_300 = " = ";
  protected final String TEXT_301 = " ";
  protected final String TEXT_302 = " ";
  protected final String TEXT_303 = "__VALUE;" + NL + "\t\tif (!";
  protected final String TEXT_304 = " && ";
  protected final String TEXT_305 = " != null) " + NL + "\t\t\treportMaxViolation(";
  protected final String TEXT_306 = ", new ";
  protected final String TEXT_307 = "(";
  protected final String TEXT_308 = "), new ";
  protected final String TEXT_309 = "(";
  protected final String TEXT_310 = "__VALUE), ";
  protected final String TEXT_311 = ", ";
  protected final String TEXT_312 = ", ";
  protected final String TEXT_313 = ");" + NL + "\t\treturn ";
  protected final String TEXT_314 = "; ";
  protected final String TEXT_315 = NL + "\t\tint ";
  protected final String TEXT_316 = " = ";
  protected final String TEXT_317 = ".compareCalendar(";
  protected final String TEXT_318 = ", ";
  protected final String TEXT_319 = "__VALUE);" + NL + "\t\tboolean ";
  protected final String TEXT_320 = " = ";
  protected final String TEXT_321 = " == 0 || ";
  protected final String TEXT_322 = " == -1;";
  protected final String TEXT_323 = NL + "\t\tint ";
  protected final String TEXT_324 = " = ";
  protected final String TEXT_325 = ".compareDuration(";
  protected final String TEXT_326 = ", ";
  protected final String TEXT_327 = "__VALUE);" + NL + "\t\tboolean ";
  protected final String TEXT_328 = " = ";
  protected final String TEXT_329 = " == 0 || ";
  protected final String TEXT_330 = " == -1;";
  protected final String TEXT_331 = NL + "\t\tboolean ";
  protected final String TEXT_332 = " = ";
  protected final String TEXT_333 = ".compareTo(";
  protected final String TEXT_334 = "__VALUE) ";
  protected final String TEXT_335 = " 0;";
  protected final String TEXT_336 = NL + "\t\tif (!";
  protected final String TEXT_337 = " && ";
  protected final String TEXT_338 = " != null) " + NL + "\t\t\treportMaxViolation(";
  protected final String TEXT_339 = ", ";
  protected final String TEXT_340 = ", ";
  protected final String TEXT_341 = "__VALUE, ";
  protected final String TEXT_342 = ", ";
  protected final String TEXT_343 = ", ";
  protected final String TEXT_344 = ");" + NL + "\t\treturn ";
  protected final String TEXT_345 = "; ";
  protected final String TEXT_346 = NL + "\t\tint length = ";
  protected final String TEXT_347 = ".";
  protected final String TEXT_348 = ";  " + NL + "\t\tboolean ";
  protected final String TEXT_349 = " = length >= ";
  protected final String TEXT_350 = ";" + NL + "\t\tif (!";
  protected final String TEXT_351 = " && ";
  protected final String TEXT_352 = " != null) " + NL + "\t\t\treportMinLengthViolation(";
  protected final String TEXT_353 = ", ";
  protected final String TEXT_354 = ", length, ";
  protected final String TEXT_355 = ", ";
  protected final String TEXT_356 = ", ";
  protected final String TEXT_357 = ");" + NL + "\t\treturn ";
  protected final String TEXT_358 = ";";
  protected final String TEXT_359 = NL + "\t\tint length = ";
  protected final String TEXT_360 = ".";
  protected final String TEXT_361 = ";  " + NL + "\t\tboolean ";
  protected final String TEXT_362 = " = length <= ";
  protected final String TEXT_363 = ";" + NL + "\t\tif (!";
  protected final String TEXT_364 = " && ";
  protected final String TEXT_365 = " != null) " + NL + "\t\t\treportMaxLengthViolation(";
  protected final String TEXT_366 = ", ";
  protected final String TEXT_367 = ", length, ";
  protected final String TEXT_368 = ", ";
  protected final String TEXT_369 = ", ";
  protected final String TEXT_370 = ");" + NL + "\t\treturn ";
  protected final String TEXT_371 = ";";
  protected final String TEXT_372 = NL + "\t\tboolean ";
  protected final String TEXT_373 = " = ";
  protected final String TEXT_374 = " > ";
  protected final String TEXT_375 = "__LOWER_BOUND && ";
  protected final String TEXT_376 = " < ";
  protected final String TEXT_377 = "__UPPER_BOUND;" + NL + "\t\tif (!";
  protected final String TEXT_378 = " && ";
  protected final String TEXT_379 = " != null) " + NL + "\t\t\treportTotalDigitsViolation(";
  protected final String TEXT_380 = ", new ";
  protected final String TEXT_381 = "(";
  protected final String TEXT_382 = "), ";
  protected final String TEXT_383 = ", ";
  protected final String TEXT_384 = ", ";
  protected final String TEXT_385 = ");";
  protected final String TEXT_386 = NL + "\t\tboolean ";
  protected final String TEXT_387 = " = ";
  protected final String TEXT_388 = ".unscaledValue().abs().toString().length() <= ";
  protected final String TEXT_389 = ";" + NL + "\t\tif (!";
  protected final String TEXT_390 = " && ";
  protected final String TEXT_391 = " != null) " + NL + "\t\t\treportTotalDigitsViolation(";
  protected final String TEXT_392 = ", ";
  protected final String TEXT_393 = ", ";
  protected final String TEXT_394 = ", ";
  protected final String TEXT_395 = ", ";
  protected final String TEXT_396 = ");";
  protected final String TEXT_397 = NL + "\t\tboolean ";
  protected final String TEXT_398 = " = ";
  protected final String TEXT_399 = ".compareTo(";
  protected final String TEXT_400 = "__LOWER_BOUND) > 0 && ";
  protected final String TEXT_401 = ".compareTo(";
  protected final String TEXT_402 = "__UPPER_BOUND) < 0;" + NL + "\t\tif (!";
  protected final String TEXT_403 = " && ";
  protected final String TEXT_404 = " != null) " + NL + "\t\t\treportTotalDigitsViolation(";
  protected final String TEXT_405 = ", ";
  protected final String TEXT_406 = ", ";
  protected final String TEXT_407 = ", ";
  protected final String TEXT_408 = ", ";
  protected final String TEXT_409 = ");";
  protected final String TEXT_410 = NL + "\t\treturn ";
  protected final String TEXT_411 = "; ";
  protected final String TEXT_412 = NL + "\t\tboolean ";
  protected final String TEXT_413 = " = ";
  protected final String TEXT_414 = ".scale() <= ";
  protected final String TEXT_415 = ";" + NL + "\t\tif (!";
  protected final String TEXT_416 = " && ";
  protected final String TEXT_417 = " != null) " + NL + "\t\t\treportFractionDigitsViolation(";
  protected final String TEXT_418 = ", ";
  protected final String TEXT_419 = ", ";
  protected final String TEXT_420 = ", ";
  protected final String TEXT_421 = ", ";
  protected final String TEXT_422 = ");" + NL + "\t\treturn ";
  protected final String TEXT_423 = "; ";
  protected final String TEXT_424 = NL + "\t\t// TODO override the constraint, if desired" + NL + "\t\t// -> uncomment the scaffolding" + NL + "\t\t// -> specify the condition that violates the constraint" + NL + "\t\t// -> verify the diagnostic details, including severity, code, and message" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tif (false)" + NL + "\t\t{" + NL + "\t\t\tif (";
  protected final String TEXT_425 = " != null)" + NL + "\t\t\t{" + NL + "\t\t\t\t";
  protected final String TEXT_426 = ".add" + NL + "\t\t\t\t\t(new ";
  protected final String TEXT_427 = NL + "\t\t\t\t\t\t(";
  protected final String TEXT_428 = ".ERROR," + NL + "\t\t\t\t\t\t DIAGNOSTIC_SOURCE," + NL + "\t\t\t\t\t\t 0," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_429 = ".INSTANCE.getString(\"_UI_GenericConstraint_diagnostic\", new Object[] { \"";
  protected final String TEXT_430 = "\", getValueLabel(";
  protected final String TEXT_431 = ", ";
  protected final String TEXT_432 = ", ";
  protected final String TEXT_433 = ") }),";
  protected final String TEXT_434 = NL + "\t\t\t\t\t\t new Object[] { ";
  protected final String TEXT_435 = " }));" + NL + "\t\t\t}" + NL + "\t\t\treturn false;" + NL + "\t\t}" + NL + "\t\treturn ";
  protected final String TEXT_436 = "validate";
  protected final String TEXT_437 = "_";
  protected final String TEXT_438 = "(";
  protected final String TEXT_439 = ", ";
  protected final String TEXT_440 = ", ";
  protected final String TEXT_441 = ");";
  protected final String TEXT_442 = NL + "\t\t// TODO implement the constraint" + NL + "\t\t// -> specify the condition that violates the constraint" + NL + "\t\t// -> verify the diagnostic details, including severity, code, and message" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tif (false)" + NL + "\t\t{" + NL + "\t\t\tif (";
  protected final String TEXT_443 = " != null)" + NL + "\t\t\t{" + NL + "\t\t\t\t";
  protected final String TEXT_444 = ".add" + NL + "\t\t\t\t\t(new ";
  protected final String TEXT_445 = NL + "\t\t\t\t\t\t(";
  protected final String TEXT_446 = ".ERROR," + NL + "\t\t\t\t\t\t DIAGNOSTIC_SOURCE," + NL + "\t\t\t\t\t\t 0," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_447 = ".INSTANCE.getString(\"_UI_GenericConstraint_diagnostic\", new Object[] { \"";
  protected final String TEXT_448 = "\", getValueLabel(";
  protected final String TEXT_449 = ", ";
  protected final String TEXT_450 = ", ";
  protected final String TEXT_451 = ") }),";
  protected final String TEXT_452 = NL + "\t\t\t\t\t\t new Object[] { ";
  protected final String TEXT_453 = " }));" + NL + "\t\t\t}" + NL + "\t\t\treturn false;" + NL + "\t\t}" + NL + "\t\treturn true;";
  protected final String TEXT_454 = NL + "\t\t// TODO override the constraint, if desired" + NL + "\t\t// -> uncomment the scaffolding" + NL + "\t\t// -> specify the condition that violates the constraint" + NL + "\t\t// -> verify the diagnostic details, including severity, code, and message" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tif (false)" + NL + "\t\t{" + NL + "\t\t\tif (";
  protected final String TEXT_455 = " != null)" + NL + "\t\t\t{" + NL + "\t\t\t\t";
  protected final String TEXT_456 = ".add" + NL + "\t\t\t\t\t(new ";
  protected final String TEXT_457 = NL + "\t\t\t\t\t\t(";
  protected final String TEXT_458 = ".ERROR," + NL + "\t\t\t\t\t\t DIAGNOSTIC_SOURCE," + NL + "\t\t\t\t\t\t 0," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_459 = ".INSTANCE.getString(\"_UI_GenericConstraint_diagnostic\", new Object[] { \"";
  protected final String TEXT_460 = "\", getObjectLabel(";
  protected final String TEXT_461 = ", ";
  protected final String TEXT_462 = ") }),";
  protected final String TEXT_463 = NL + "\t\t\t\t\t\t new Object[] { ";
  protected final String TEXT_464 = " }));" + NL + "\t\t\t}" + NL + "\t\t\treturn false;" + NL + "\t\t}" + NL + "\t\treturn ";
  protected final String TEXT_465 = "validate";
  protected final String TEXT_466 = "_";
  protected final String TEXT_467 = "(";
  protected final String TEXT_468 = ", ";
  protected final String TEXT_469 = ", ";
  protected final String TEXT_470 = ");";
  protected final String TEXT_471 = NL + "\t\treturn ";
  protected final String TEXT_472 = ".";
  protected final String TEXT_473 = "(";
  protected final String TEXT_474 = ", ";
  protected final String TEXT_475 = ");";
  protected final String TEXT_476 = NL + "        if (";
  protected final String TEXT_477 = " == null)" + NL + "        {" + NL + "\t\t\t";
  protected final String TEXT_478 = ".Helper helper = OCL_ENV.createOCLHelper();" + NL + "\t\t\thelper.setContext(";
  protected final String TEXT_479 = ");" + NL + "\t\t\t" + NL + "\t\t\t";
  protected final String TEXT_480 = " ocl = ";
  protected final String TEXT_481 = ".getEAnnotation(OCL_ANNOTATION_SOURCE);" + NL + "\t\t\tString expr = ocl.getDetails().get(\"";
  protected final String TEXT_482 = "\");";
  protected final String TEXT_483 = NL + "\t\t\t" + NL + "\t\t\ttry" + NL + "\t\t\t{" + NL + "\t\t\t\t";
  protected final String TEXT_484 = " = helper.createInvariant(expr);" + NL + "\t\t\t}" + NL + "\t\t\tcatch (";
  protected final String TEXT_485 = " e)" + NL + "\t\t\t{" + NL + "\t\t\t\tthrow new UnsupportedOperationException(e.getLocalizedMessage());" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\t" + NL + "\t\t";
  protected final String TEXT_486 = "<";
  protected final String TEXT_487 = ", ?, ?> query = OCL_ENV.createQuery(";
  protected final String TEXT_488 = ");" + NL + "\t\t" + NL + "\t\tif (!query.check(";
  protected final String TEXT_489 = "))" + NL + "\t\t{" + NL + "\t\t\tif (";
  protected final String TEXT_490 = " != null)" + NL + "\t\t\t{" + NL + "\t\t\t\t";
  protected final String TEXT_491 = ".add" + NL + "\t\t\t\t\t(new ";
  protected final String TEXT_492 = NL + "\t\t\t\t\t\t(";
  protected final String TEXT_493 = ".ERROR," + NL + "\t\t\t\t\t\t DIAGNOSTIC_SOURCE," + NL + "\t\t\t\t\t\t 0," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_494 = ".INSTANCE.getString(\"_UI_GenericConstraint_diagnostic\", new Object[] { \"";
  protected final String TEXT_495 = "\", getObjectLabel(";
  protected final String TEXT_496 = ", ";
  protected final String TEXT_497 = ") }),";
  protected final String TEXT_498 = NL + "\t\t\t\t\t\t new Object[] { ";
  protected final String TEXT_499 = " }));" + NL + "\t\t\t}" + NL + "\t\t\treturn false;" + NL + "\t\t}" + NL + "\t\treturn true;";
  protected final String TEXT_500 = NL + "\t\t// TODO implement the constraint" + NL + "\t\t// -> specify the condition that violates the constraint" + NL + "\t\t// -> verify the diagnostic details, including severity, code, and message" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tif (false)" + NL + "\t\t{" + NL + "\t\t\tif (";
  protected final String TEXT_501 = " != null)" + NL + "\t\t\t{" + NL + "\t\t\t\t";
  protected final String TEXT_502 = ".add" + NL + "\t\t\t\t\t(new ";
  protected final String TEXT_503 = NL + "\t\t\t\t\t\t(";
  protected final String TEXT_504 = ".ERROR," + NL + "\t\t\t\t\t\t DIAGNOSTIC_SOURCE," + NL + "\t\t\t\t\t\t 0," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_505 = ".INSTANCE.getString(\"_UI_GenericConstraint_diagnostic\", new Object[] { \"";
  protected final String TEXT_506 = "\", getObjectLabel(";
  protected final String TEXT_507 = ", ";
  protected final String TEXT_508 = ") }),";
  protected final String TEXT_509 = NL + "\t\t\t\t\t\t new Object[] { ";
  protected final String TEXT_510 = " }));" + NL + "\t\t\t}" + NL + "\t\t\treturn false;" + NL + "\t\t}" + NL + "\t\treturn true;";
  protected final String TEXT_511 = NL + "\t}" + NL;
  protected final String TEXT_512 = NL + "} //";
  protected final String TEXT_513 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
/**
 * <copyright>
 *
 * Copyright (c) 2002, 2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 */

     final String oclNsURI = "http://www.eclipse.org/ocl/examples/OCL"; 
    GenPackage genPackage = (GenPackage)argument; GenModel genModel=genPackage.getGenModel();
    final String singleWildcard = genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50 ? "<?>" : "";
    stringBuffer.append(TEXT_1);
    stringBuffer.append(TEXT_2);
    {GenBase copyrightHolder = argument instanceof GenBase ? (GenBase)argument : argument instanceof Object[] && ((Object[])argument)[0] instanceof GenBase ? (GenBase)((Object[])argument)[0] : null;
    if (copyrightHolder != null && copyrightHolder.hasCopyright()) {
    stringBuffer.append(TEXT_3);
    stringBuffer.append(copyrightHolder.getCopyright(copyrightHolder.getGenModel().getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_4);
    }}
    stringBuffer.append(TEXT_5);
    stringBuffer.append("$");
    stringBuffer.append(TEXT_6);
    stringBuffer.append("$");
    stringBuffer.append(TEXT_7);
    stringBuffer.append(genPackage.getUtilitiesPackageName());
    stringBuffer.append(TEXT_8);
    genModel.addImport("java.util.Map");
    final String _Map = genModel.useGenerics() ? "Map<" + genModel.getImportedName("java.lang.Object") + ", " + genModel.getImportedName("java.lang.Object") + ">" : "Map";
    genModel.addImport("org.eclipse.emf.common.util.DiagnosticChain");
    genModel.addImport("org.eclipse.emf.ecore.EPackage");
    if (!genPackage.hasJavaLangConflict() && !genPackage.getUtilitiesPackageName().equals(genPackage.getInterfacePackageName())) genModel.addImport(genPackage.getInterfacePackageName() + ".*");
    genModel.markImportLocation(stringBuffer);
    genModel.addPseudoImport("org.eclipse.emf.ecore.EValidator.Descriptor");
    genModel.addPseudoImport("org.eclipse.emf.ecore.EValidator.Registry");
    genModel.addPseudoImport("org.eclipse.emf.ecore.EValidator.SubstitutionLabelProvider");
    genModel.addPseudoImport("org.eclipse.emf.ecore.EValidator.PatternMatcher");
    stringBuffer.append(TEXT_9);
    stringBuffer.append(genPackage.getQualifiedPackageInterfaceName());
    stringBuffer.append(TEXT_10);
    stringBuffer.append(genPackage.getValidatorClassName());
    stringBuffer.append(TEXT_11);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EObjectValidator"));
    stringBuffer.append(TEXT_12);
    if (genModel.getCopyrightText() != null) {
    stringBuffer.append(TEXT_13);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_14);
    stringBuffer.append(genModel.getCopyrightText());
    stringBuffer.append(TEXT_15);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_16);
    }
    stringBuffer.append(TEXT_17);
    stringBuffer.append(genPackage.getValidatorClassName());
    stringBuffer.append(TEXT_18);
    stringBuffer.append(genPackage.getValidatorClassName());
    stringBuffer.append(TEXT_19);
    stringBuffer.append(genPackage.getInterfacePackageName());
    stringBuffer.append(TEXT_20);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_21);
    int count = 0; for (GenClass genClass : genPackage.getGenClasses()) {
    for (GenOperation genOperation : genClass.getInvariantOperations()) {
    stringBuffer.append(TEXT_22);
    stringBuffer.append(genOperation.getFormattedName());
    stringBuffer.append(TEXT_23);
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_24);
    stringBuffer.append(genClass.getOperationID(genOperation));
    stringBuffer.append(TEXT_25);
    stringBuffer.append(++count);
    stringBuffer.append(TEXT_26);
    }}
    stringBuffer.append(TEXT_27);
    stringBuffer.append(count);
    stringBuffer.append(TEXT_28);
    for (GenPackage baseGenPackage : genPackage.getAllValidatorBaseGenPackages()) {
    stringBuffer.append(TEXT_29);
    stringBuffer.append(baseGenPackage.getImportedValidatorClassName());
    stringBuffer.append(TEXT_30);
    stringBuffer.append(genPackage.getValidatorPackageUniqueSafeName(baseGenPackage));
    stringBuffer.append(TEXT_31);
    }
    boolean hasOCL = false;
for (GenClassifier genClassifier : genPackage.getGenClassifiers()) {
    EClassifier eClassifier = genClassifier.getEcoreClassifier();
    EAnnotation annotation = eClassifier.getEAnnotation(oclNsURI);
    if (annotation != null) {
        for (String constraint : EcoreUtil.getConstraints(eClassifier)) {
        
            if (annotation.getDetails().get(constraint) != null) {
                hasOCL = true;
    stringBuffer.append(TEXT_32);
    stringBuffer.append(constraint);
    stringBuffer.append(TEXT_33);
    stringBuffer.append(genModel.getImportedName("org.eclipse.ocl.ecore.Constraint"));
    stringBuffer.append(TEXT_34);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_35);
    stringBuffer.append(constraint);
    stringBuffer.append(TEXT_36);
    }
        }
    }
}

if (hasOCL) {
    stringBuffer.append(TEXT_37);
    stringBuffer.append(oclNsURI);
    stringBuffer.append(TEXT_38);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_39);
    stringBuffer.append(genModel.getImportedName("org.eclipse.ocl.ecore.OCL"));
    stringBuffer.append(TEXT_40);
    stringBuffer.append(genModel.getImportedName("org.eclipse.ocl.ecore.OCL"));
    stringBuffer.append(TEXT_41);
    }
    stringBuffer.append(TEXT_42);
    stringBuffer.append(genPackage.getValidatorClassName());
    stringBuffer.append(TEXT_43);
    for (GenPackage baseGenPackage : genPackage.getAllValidatorBaseGenPackages()) {
    stringBuffer.append(TEXT_44);
    stringBuffer.append(genPackage.getValidatorPackageUniqueSafeName(baseGenPackage));
    stringBuffer.append(TEXT_45);
    stringBuffer.append(baseGenPackage.getImportedValidatorClassName());
    stringBuffer.append(TEXT_46);
    }
    stringBuffer.append(TEXT_47);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_48);
    }
    stringBuffer.append(TEXT_49);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_50);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_51);
    }
    stringBuffer.append(TEXT_52);
    stringBuffer.append(_Map);
    stringBuffer.append(TEXT_53);
    for (GenClassifier genClassifier : genPackage.getGenClassifiers()) {
    stringBuffer.append(TEXT_54);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_55);
    stringBuffer.append(genPackage.getClassifierID(genClassifier));
    stringBuffer.append(TEXT_56);
    if (genClassifier instanceof GenDataType) { GenDataType genDataType = (GenDataType)genClassifier;
    if (genDataType.isPrimitiveType()) {
    stringBuffer.append(TEXT_57);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_58);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_59);
    stringBuffer.append(genDataType.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_60);
    } else if (genDataType.isObjectType()) {
    stringBuffer.append(TEXT_61);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_62);
    } else {
    stringBuffer.append(TEXT_63);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_64);
    stringBuffer.append(genDataType.getImportedWildcardInstanceClassName());
    stringBuffer.append(TEXT_65);
    }
    } else { GenClass genClass = (GenClass)genClassifier;
    stringBuffer.append(TEXT_66);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genClass.getImportedWildcardInstanceClassName());
    stringBuffer.append(TEXT_68);
    }
    }
    stringBuffer.append(TEXT_69);
    for (GenClassifier genClassifier : genPackage.getGenClassifiers()) {String result = "result".equals(genClassifier.getSafeUncapName()) ? "theResult" : "result"; String diagnostics = "diagnostics".equals(genClassifier.getSafeUncapName()) ? "theDiagnostics" : "diagnostics"; String item = "item".equals(genClassifier.getSafeUncapName()) ? "theItem" : "item"; String context = "context".equals(genClassifier.getSafeUncapName()) ? "theContext" : "context";
    stringBuffer.append(TEXT_70);
    stringBuffer.append(genClassifier.getName());
    stringBuffer.append(TEXT_71);
    stringBuffer.append(genClassifier.getImportedWildcardInstanceClassName());
    stringBuffer.append(TEXT_72);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_73);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_74);
    stringBuffer.append(_Map);
    stringBuffer.append(TEXT_75);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_76);
    if (genClassifier.getAllGenConstraints().isEmpty()) {
    stringBuffer.append(TEXT_77);
    } else if (genClassifier.hasOnlyDefaultConstraints()) {
    stringBuffer.append(TEXT_78);
    if (!((GenClass)genClassifier).isEObjectExtension()){
    stringBuffer.append(TEXT_79);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EObject"));
    stringBuffer.append(TEXT_80);
    }
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_81);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_82);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_83);
    } else { boolean first = true;
    for (String constraint : genClassifier.getAllGenConstraints()) {GenClassifier constraintImplementor = genClassifier.getConstraintImplementor(constraint); 
  String delegate = constraintImplementor == null || constraintImplementor.getGenPackage() == genPackage ? "" : genPackage.getValidatorPackageUniqueSafeName(constraintImplementor.getGenPackage()) + "Validator."; 
  String cast = constraintImplementor == null && genClassifier instanceof GenClass && !((GenClass)genClassifier).isEObjectExtension() ? "(" + genModel.getImportedName("org.eclipse.emf.ecore.EObject") + ")" : "";
  String accessor = constraintImplementor != null && genClassifier instanceof GenDataType && !((GenDataType)genClassifier).isPrimitiveType() && ((GenDataType)constraintImplementor).isPrimitiveType() ? "." + ((GenDataType)constraintImplementor).getPrimitiveValueFunction() + "()" : "";
    if (first) { first = false;
    stringBuffer.append(TEXT_84);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_85);
    stringBuffer.append(delegate);
    stringBuffer.append(TEXT_86);
    stringBuffer.append(constraintImplementor == null ? "" : constraintImplementor.getName());
    stringBuffer.append(TEXT_87);
    stringBuffer.append(constraint);
    stringBuffer.append(TEXT_88);
    stringBuffer.append(cast);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(accessor);
    stringBuffer.append(TEXT_89);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_90);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_91);
    } else {
    stringBuffer.append(TEXT_92);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_93);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_94);
    stringBuffer.append(delegate);
    stringBuffer.append(TEXT_95);
    stringBuffer.append(constraintImplementor == null ? "" : constraintImplementor.getName());
    stringBuffer.append(TEXT_96);
    stringBuffer.append(constraint);
    stringBuffer.append(TEXT_97);
    stringBuffer.append(cast);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(accessor);
    stringBuffer.append(TEXT_98);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_99);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_100);
    }
    }
    stringBuffer.append(TEXT_101);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_102);
    }
    stringBuffer.append(TEXT_103);
    for (String constraint : genClassifier.getGenConstraints()) 
{GenClassifier constraintDelegate = genClassifier.getConstraintDelegate(constraint); 
  String constant = genClassifier.getClassifierID() + "__" + CodeGenUtil.format(constraint, '_', null, false, false).toUpperCase();
  String delegate = constraintDelegate == null || constraintDelegate.getGenPackage() == genPackage ? "" : genPackage.getValidatorPackageUniqueSafeName(constraintDelegate.getGenPackage()) + "Validator."; 
  String cast = constraintDelegate == null && genClassifier instanceof GenClass && !((GenClass)genClassifier).isEObjectExtension() ? "(" + genModel.getImportedName("org.eclipse.emf.ecore.EObject") + ")" : "";
  String accessor = constraintDelegate != null && genClassifier instanceof GenDataType && !((GenDataType)genClassifier).isPrimitiveType() && ((GenDataType)constraintDelegate).isPrimitiveType() ? "." + ((GenDataType)constraintDelegate).getPrimitiveValueFunction() + "()" : "";
    if (genClassifier instanceof GenDataType) { GenDataType genDataType = (GenDataType)genClassifier;
    if (constraint.equals("Min") && genDataType.getMinLiteral() != null) {
    stringBuffer.append(TEXT_104);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_105);
    stringBuffer.append(constraint);
    stringBuffer.append(TEXT_106);
    stringBuffer.append(genDataType.getImportedInstanceClassName());
    stringBuffer.append(TEXT_107);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_108);
    stringBuffer.append(genDataType.getStaticValue(genDataType.getMinLiteral()));
    stringBuffer.append(TEXT_109);
    } else if (constraint.equals("Max") && genDataType.getMaxLiteral() != null) {
    stringBuffer.append(TEXT_110);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_111);
    stringBuffer.append(constraint);
    stringBuffer.append(TEXT_112);
    stringBuffer.append(genDataType.getImportedInstanceClassName());
    stringBuffer.append(TEXT_113);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_114);
    stringBuffer.append(genDataType.getStaticValue(genDataType.getMaxLiteral()));
    stringBuffer.append(TEXT_115);
    } else if (constraint.equals("TotalDigits") && genDataType.getTotalDigits() != -1 && !"java.math.BigDecimal".equals(genDataType.getQualifiedInstanceClassName())) { String value = "1"; for (int digitCount = genDataType.getTotalDigits(); digitCount > 0; --digitCount) value += "0"; 
    stringBuffer.append(TEXT_116);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_117);
    stringBuffer.append(constraint);
    stringBuffer.append(TEXT_118);
    stringBuffer.append(genDataType.getImportedInstanceClassName());
    stringBuffer.append(TEXT_119);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_120);
    stringBuffer.append(genDataType.getStaticValue(value));
    stringBuffer.append(TEXT_121);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_122);
    stringBuffer.append(constraint);
    stringBuffer.append(TEXT_123);
    stringBuffer.append(genDataType.getImportedInstanceClassName());
    stringBuffer.append(TEXT_124);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_125);
    stringBuffer.append(genDataType.getStaticValue("-" + value));
    stringBuffer.append(TEXT_126);
    } else if (constraint.equals("Pattern") && !genDataType.getPatterns().isEmpty()) {
    stringBuffer.append(TEXT_127);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_128);
    stringBuffer.append(constraint);
    stringBuffer.append(TEXT_129);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_130);
    for (Iterator<List<String>> k = genDataType.getPatterns().iterator(); k.hasNext(); ) { List<String> patternList = k.next();
    stringBuffer.append(TEXT_131);
    for (Iterator<String> p = patternList.iterator(); p.hasNext(); ) { String pattern = p.next();
    stringBuffer.append(TEXT_132);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.xml.type.util.XMLTypeUtil"));
    stringBuffer.append(TEXT_133);
    stringBuffer.append(pattern);
    stringBuffer.append(TEXT_134);
    stringBuffer.append(p.hasNext() ? "," : "");
    }
    stringBuffer.append(TEXT_135);
    stringBuffer.append(k.hasNext() ? "," : "");
    }
    stringBuffer.append(TEXT_136);
    } else if (constraint.equals("Enumeration") && !genDataType.getEnumerationLiterals().isEmpty()) {
    stringBuffer.append(TEXT_137);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_138);
    stringBuffer.append(constraint);
    stringBuffer.append(TEXT_139);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_140);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_141);
    for (Iterator<String> k = genDataType.getEnumerationLiterals().iterator(); k.hasNext(); ) { String literal = k.next();
    stringBuffer.append(TEXT_142);
    if (genDataType.isPrimitiveType()) {
    stringBuffer.append(TEXT_143);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_144);
    }
    stringBuffer.append(genDataType.getStaticValue(literal));
    if (genDataType.isPrimitiveType()) {
    stringBuffer.append(TEXT_145);
    }
    stringBuffer.append(k.hasNext() ? "," : "");
    }
    stringBuffer.append(TEXT_146);
    }
    }
    stringBuffer.append(TEXT_147);
    stringBuffer.append(constraint);
    stringBuffer.append(TEXT_148);
    stringBuffer.append(genClassifier.getFormattedName());
    stringBuffer.append(TEXT_149);
    stringBuffer.append(genClassifier.getName());
    stringBuffer.append(TEXT_150);
    stringBuffer.append(constraint);
    stringBuffer.append(TEXT_151);
    stringBuffer.append(genClassifier.getImportedWildcardInstanceClassName());
    stringBuffer.append(TEXT_152);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_153);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_154);
    stringBuffer.append(_Map);
    stringBuffer.append(TEXT_155);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_156);
    if (genClassifier instanceof GenDataType) { GenDataType genDataType = (GenDataType)genClassifier;
    if (constraint.equals("ItemType") && genDataType.getItemType() != null) { GenDataType itemType = genDataType.getItemType(); String itemDelegate = itemType.getGenPackage() == genPackage ? "" : genPackage.getValidatorPackageUniqueSafeName(itemType.getGenPackage()) + "Validator.";
    stringBuffer.append(TEXT_157);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_158);
    stringBuffer.append(genModel.getImportedName("java.util.Iterator"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_159);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_160);
    if (itemType.getGenPackage().hasConstraints()) {
    stringBuffer.append(TEXT_161);
    stringBuffer.append(itemType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_162);
    stringBuffer.append(itemDelegate);
    stringBuffer.append(TEXT_163);
    stringBuffer.append(itemType.getName());
    stringBuffer.append(TEXT_164);
    if (itemType.isPrimitiveType()) {
    stringBuffer.append(TEXT_165);
    }
    if (!itemType.isObjectType()) {
    stringBuffer.append(TEXT_166);
    stringBuffer.append(itemType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_167);
    }
    stringBuffer.append(item);
    if (itemType.isPrimitiveType()) {
    stringBuffer.append(TEXT_168);
    stringBuffer.append(itemType.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_169);
    }
    stringBuffer.append(TEXT_170);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_171);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_172);
    } else {
    stringBuffer.append(TEXT_173);
    stringBuffer.append(itemType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_174);
    }
    stringBuffer.append(TEXT_175);
    stringBuffer.append(itemType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_176);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_177);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_178);
    } else if (constraint.equals("MemberTypes") && !genDataType.getMemberTypes().isEmpty()) {
    stringBuffer.append(TEXT_179);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicDiagnostic"));
    stringBuffer.append(TEXT_180);
    for (ListIterator<GenDataType> k = genDataType.getMemberTypes().listIterator(); k.hasNext(); ) { GenDataType memberType = k.next(); String memberDelegate = memberType.getGenPackage() == genPackage ? "" : genPackage.getValidatorPackageUniqueSafeName(memberType.getGenPackage()) + "Validator.";
    if (genDataType.isPrimitiveType()) {
    stringBuffer.append(TEXT_181);
    stringBuffer.append(memberDelegate);
    stringBuffer.append(TEXT_182);
    stringBuffer.append(memberType.getName());
    stringBuffer.append(TEXT_183);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_184);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_185);
    } else {
    stringBuffer.append(TEXT_186);
    stringBuffer.append(memberType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_187);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_188);
    if (memberType.getGenPackage().hasConstraints()) {
    stringBuffer.append(TEXT_189);
    stringBuffer.append(memberDelegate);
    stringBuffer.append(TEXT_190);
    stringBuffer.append(memberType.getName());
    stringBuffer.append(TEXT_191);
    if (memberType.isPrimitiveType()) {
    stringBuffer.append(TEXT_192);
    }
    if (!memberType.isObjectType() && !memberType.getQualifiedInstanceClassName().equals(genDataType.getQualifiedInstanceClassName())) {
    stringBuffer.append(TEXT_193);
    stringBuffer.append(memberType.getImportedWildcardObjectInstanceClassName());
    stringBuffer.append(TEXT_194);
    }
    stringBuffer.append(genClassifier.getSafeUncapName());
    if (memberType.isPrimitiveType()) {
    stringBuffer.append(TEXT_195);
    stringBuffer.append(memberType.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_196);
    }
    stringBuffer.append(TEXT_197);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_198);
    }
    stringBuffer.append(TEXT_199);
    }
    }
    if (genModel.useGenerics()) {
    stringBuffer.append(TEXT_200);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_201);
    } else {
    stringBuffer.append(TEXT_202);
    stringBuffer.append(genModel.getImportedName("java.util.List"));
    stringBuffer.append(TEXT_203);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_204);
    }
    stringBuffer.append(TEXT_205);
    for (ListIterator<GenDataType> k = genDataType.getMemberTypes().listIterator(); k.hasNext(); ) { GenDataType memberType = k.next(); String memberDelegate = memberType.getGenPackage() == genPackage ? "" : genPackage.getValidatorPackageUniqueSafeName(memberType.getGenPackage()) + "Validator.";
    if (genDataType.isPrimitiveType()) {
    stringBuffer.append(TEXT_206);
    stringBuffer.append(memberDelegate);
    stringBuffer.append(TEXT_207);
    stringBuffer.append(memberType.getName());
    stringBuffer.append(TEXT_208);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_209);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_210);
    } else {
    stringBuffer.append(TEXT_211);
    stringBuffer.append(memberType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_212);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_213);
    if (memberType.getGenPackage().hasConstraints()) {
    stringBuffer.append(TEXT_214);
    stringBuffer.append(memberDelegate);
    stringBuffer.append(TEXT_215);
    stringBuffer.append(memberType.getName());
    stringBuffer.append(TEXT_216);
    if (memberType.isPrimitiveType()) {
    stringBuffer.append(TEXT_217);
    }
    if (!memberType.isObjectType() && !memberType.getQualifiedInstanceClassName().equals(genDataType.getQualifiedInstanceClassName())) {
    stringBuffer.append(TEXT_218);
    stringBuffer.append(memberType.getImportedWildcardObjectInstanceClassName());
    stringBuffer.append(TEXT_219);
    }
    stringBuffer.append(genClassifier.getSafeUncapName());
    if (memberType.isPrimitiveType()) {
    stringBuffer.append(TEXT_220);
    stringBuffer.append(memberType.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_221);
    }
    stringBuffer.append(TEXT_222);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_223);
    }
    stringBuffer.append(TEXT_224);
    }
    }
    stringBuffer.append(TEXT_225);
    } else if (constraint.equals("Pattern") && !genDataType.getPatterns().isEmpty()) {
    stringBuffer.append(TEXT_226);
    stringBuffer.append(genDataType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_227);
    if (genDataType.isPrimitiveType()) {
    stringBuffer.append(TEXT_228);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_229);
    }
    stringBuffer.append(genDataType.getSafeUncapName());
    if (genDataType.isPrimitiveType()) {
    stringBuffer.append(TEXT_230);
    }
    stringBuffer.append(TEXT_231);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_232);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_233);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_234);
    } else if (constraint.equals("Enumeration") && !genDataType.getEnumerationLiterals().isEmpty()) { String variable = genDataType.getSafeUncapName();
    if (genDataType.isPrimitiveType()) { variable = variable + "Object";
    stringBuffer.append(TEXT_235);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_236);
    stringBuffer.append(variable);
    stringBuffer.append(TEXT_237);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_238);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_239);
    }
    stringBuffer.append(TEXT_240);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_241);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_242);
    stringBuffer.append(variable);
    stringBuffer.append(TEXT_243);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_244);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_245);
    stringBuffer.append(genDataType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_246);
    stringBuffer.append(variable);
    stringBuffer.append(TEXT_247);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_248);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_249);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_250);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_251);
    } else if (constraint.equals("Min") && genDataType.getMinLiteral() != null) {
    if (genDataType.isPrimitiveType()) {
    stringBuffer.append(TEXT_252);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_253);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_254);
    stringBuffer.append(genDataType.isMinInclusive() ? ">=" : ">");
    stringBuffer.append(TEXT_255);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_256);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_257);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_258);
    stringBuffer.append(genDataType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_259);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_260);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_261);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_262);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_263);
    stringBuffer.append(genDataType.isMinInclusive() ? "true" : "false");
    stringBuffer.append(TEXT_264);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_265);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_266);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_267);
    } else { String comparison = "comparison".equals(genClassifier.getSafeUncapName()) ? "theComparison" : "comparison";
    if (genDataType.isXMLCalendar()) {
    stringBuffer.append(TEXT_268);
    stringBuffer.append(comparison);
    stringBuffer.append(TEXT_269);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.xml.type.util.XMLTypeUtil"));
    stringBuffer.append(TEXT_270);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_271);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_272);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_273);
    if (genDataType.isMinInclusive()) {
    stringBuffer.append(comparison);
    stringBuffer.append(TEXT_274);
    }
    stringBuffer.append(comparison);
    stringBuffer.append(TEXT_275);
    } else if (genDataType.isXMLDuration()) {
    stringBuffer.append(TEXT_276);
    stringBuffer.append(comparison);
    stringBuffer.append(TEXT_277);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.xml.type.util.XMLTypeUtil"));
    stringBuffer.append(TEXT_278);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_279);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_280);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_281);
    if (genDataType.isMinInclusive()) {
    stringBuffer.append(comparison);
    stringBuffer.append(TEXT_282);
    }
    stringBuffer.append(comparison);
    stringBuffer.append(TEXT_283);
    } else {
    stringBuffer.append(TEXT_284);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_285);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_286);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_287);
    stringBuffer.append(genDataType.isMinInclusive() ? ">=" : ">");
    stringBuffer.append(TEXT_288);
    }
    stringBuffer.append(TEXT_289);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_290);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_291);
    stringBuffer.append(genDataType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_292);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_293);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_294);
    stringBuffer.append(genDataType.isMinInclusive() ? "true" : "false");
    stringBuffer.append(TEXT_295);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_296);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_297);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_298);
    }
    } else if (constraint.equals("Max") && genDataType.getMaxLiteral() != null) {
    if (genDataType.isPrimitiveType()) {
    stringBuffer.append(TEXT_299);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_300);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_301);
    stringBuffer.append(genDataType.isMaxInclusive() ? "<=" : "<");
    stringBuffer.append(TEXT_302);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_303);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_304);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_305);
    stringBuffer.append(genDataType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_306);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_307);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_308);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_309);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_310);
    stringBuffer.append(genDataType.isMaxInclusive() ? "true" : "false");
    stringBuffer.append(TEXT_311);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_312);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_313);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_314);
    } else { String comparison = "comparison".equals(genClassifier.getSafeUncapName()) ? "theComparison" : "comparison";
    if (genDataType.isXMLCalendar()) {
    stringBuffer.append(TEXT_315);
    stringBuffer.append(comparison);
    stringBuffer.append(TEXT_316);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.xml.type.util.XMLTypeUtil"));
    stringBuffer.append(TEXT_317);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_318);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_319);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_320);
    if (genDataType.isMaxInclusive()) {
    stringBuffer.append(comparison);
    stringBuffer.append(TEXT_321);
    }
    stringBuffer.append(comparison);
    stringBuffer.append(TEXT_322);
    } else if (genDataType.isXMLDuration()) {
    stringBuffer.append(TEXT_323);
    stringBuffer.append(comparison);
    stringBuffer.append(TEXT_324);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.xml.type.util.XMLTypeUtil"));
    stringBuffer.append(TEXT_325);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_326);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_327);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_328);
    if (genDataType.isMaxInclusive()) {
    stringBuffer.append(comparison);
    stringBuffer.append(TEXT_329);
    }
    stringBuffer.append(comparison);
    stringBuffer.append(TEXT_330);
    } else {
    stringBuffer.append(TEXT_331);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_332);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_333);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_334);
    stringBuffer.append(genDataType.isMaxInclusive() ? "<=" : "<");
    stringBuffer.append(TEXT_335);
    }
    stringBuffer.append(TEXT_336);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_337);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_338);
    stringBuffer.append(genDataType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_339);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_340);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_341);
    stringBuffer.append(genDataType.isMaxInclusive() ? "true" : "false");
    stringBuffer.append(TEXT_342);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_343);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_344);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_345);
    }
    } else if (constraint.equals("MinLength") && genDataType.getMinLength() != -1) {
    stringBuffer.append(TEXT_346);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_347);
    stringBuffer.append(genDataType.getLengthAccessorFunction());
    stringBuffer.append(TEXT_348);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_349);
    stringBuffer.append(genDataType.getMinLength());
    stringBuffer.append(TEXT_350);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_351);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_352);
    stringBuffer.append(genDataType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_353);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_354);
    stringBuffer.append(genDataType.getMinLength());
    stringBuffer.append(TEXT_355);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_356);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_357);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_358);
    } else if (constraint.equals("MaxLength") && genDataType.getMaxLength() != -1) {
    stringBuffer.append(TEXT_359);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_360);
    stringBuffer.append(genDataType.getLengthAccessorFunction());
    stringBuffer.append(TEXT_361);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_362);
    stringBuffer.append(genDataType.getMaxLength());
    stringBuffer.append(TEXT_363);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_364);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_365);
    stringBuffer.append(genDataType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_366);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_367);
    stringBuffer.append(genDataType.getMaxLength());
    stringBuffer.append(TEXT_368);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_369);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_370);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_371);
    } else if (constraint.equals("TotalDigits") && genDataType.getTotalDigits() != -1) {
    if (genDataType.isPrimitiveType()) {
    stringBuffer.append(TEXT_372);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_373);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_374);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_375);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_376);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_377);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_378);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_379);
    stringBuffer.append(genDataType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_380);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_381);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_382);
    stringBuffer.append(genDataType.getTotalDigits());
    stringBuffer.append(TEXT_383);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_384);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_385);
    } else if ("java.math.BigDecimal".equals(genDataType.getQualifiedInstanceClassName())) {
    stringBuffer.append(TEXT_386);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_387);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_388);
    stringBuffer.append(genDataType.getTotalDigits());
    stringBuffer.append(TEXT_389);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_390);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_391);
    stringBuffer.append(genDataType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_392);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_393);
    stringBuffer.append(genDataType.getTotalDigits());
    stringBuffer.append(TEXT_394);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_395);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_396);
    } else {
    stringBuffer.append(TEXT_397);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_398);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_399);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_400);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_401);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_402);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_403);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_404);
    stringBuffer.append(genDataType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_405);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_406);
    stringBuffer.append(genDataType.getTotalDigits());
    stringBuffer.append(TEXT_407);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_408);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_409);
    }
    stringBuffer.append(TEXT_410);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_411);
    } else if (constraint.equals("FractionDigits") && genDataType.getFractionDigits() != -1 && "java.math.BigDecimal".equals(genDataType.getQualifiedInstanceClassName())) {
    stringBuffer.append(TEXT_412);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_413);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_414);
    stringBuffer.append(genDataType.getFractionDigits());
    stringBuffer.append(TEXT_415);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_416);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_417);
    stringBuffer.append(genDataType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_418);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_419);
    stringBuffer.append(genDataType.getFractionDigits());
    stringBuffer.append(TEXT_420);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_421);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_422);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_423);
    } else if (constraintDelegate != null) {
    stringBuffer.append(TEXT_424);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_425);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_426);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicDiagnostic"));
    stringBuffer.append(TEXT_427);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_428);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.plugin.EcorePlugin"));
    stringBuffer.append(TEXT_429);
    stringBuffer.append(constraint);
    stringBuffer.append(TEXT_430);
    stringBuffer.append(genDataType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_431);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_432);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_433);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_434);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_435);
    stringBuffer.append(delegate);
    stringBuffer.append(TEXT_436);
    stringBuffer.append(constraintDelegate.getName());
    stringBuffer.append(TEXT_437);
    stringBuffer.append(constraint);
    stringBuffer.append(TEXT_438);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(accessor);
    stringBuffer.append(TEXT_439);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_440);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_441);
    } else {
    stringBuffer.append(TEXT_442);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_443);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_444);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicDiagnostic"));
    stringBuffer.append(TEXT_445);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_446);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.plugin.EcorePlugin"));
    stringBuffer.append(TEXT_447);
    stringBuffer.append(constraint);
    stringBuffer.append(TEXT_448);
    stringBuffer.append(genDataType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_449);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_450);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_451);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_452);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_453);
    }
    } else if (constraintDelegate != null) {
    stringBuffer.append(TEXT_454);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_455);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_456);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicDiagnostic"));
    stringBuffer.append(TEXT_457);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_458);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.plugin.EcorePlugin"));
    stringBuffer.append(TEXT_459);
    stringBuffer.append(constraint);
    stringBuffer.append(TEXT_460);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_461);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_462);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_463);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_464);
    stringBuffer.append(delegate);
    stringBuffer.append(TEXT_465);
    stringBuffer.append(constraintDelegate.getName());
    stringBuffer.append(TEXT_466);
    stringBuffer.append(constraint);
    stringBuffer.append(TEXT_467);
    stringBuffer.append(cast);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_468);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_469);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_470);
    } else { GenOperation genOperation = ((GenClass)genClassifier).getInvariantOperation(constraint); if (genOperation != null) {
    stringBuffer.append(TEXT_471);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_472);
    stringBuffer.append(constraint);
    stringBuffer.append(TEXT_473);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_474);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_475);
    } else { EAnnotation annotation = genClassifier.getEcoreClassifier().getEAnnotation(oclNsURI); if (annotation != null && annotation.getDetails().get(constraint) != null) {
        String invOCL = genClassifier.getSafeUncapName() + "_" + constraint + "InvOCL";
    stringBuffer.append(TEXT_476);
    stringBuffer.append(invOCL);
    stringBuffer.append(TEXT_477);
    stringBuffer.append(genModel.getImportedName("org.eclipse.ocl.ecore.OCL"));
    stringBuffer.append(TEXT_478);
    stringBuffer.append(genClassifier.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_479);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EAnnotation"));
    stringBuffer.append(TEXT_480);
    stringBuffer.append(genClassifier.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_481);
    stringBuffer.append(constraint);
    stringBuffer.append(TEXT_482);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_483);
    stringBuffer.append(invOCL);
    stringBuffer.append(TEXT_484);
    stringBuffer.append(genModel.getImportedName("org.eclipse.ocl.ParserException"));
    stringBuffer.append(TEXT_485);
    stringBuffer.append(genModel.getImportedName("org.eclipse.ocl.Query"));
    stringBuffer.append(TEXT_486);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EClassifier"));
    stringBuffer.append(TEXT_487);
    stringBuffer.append(invOCL);
    stringBuffer.append(TEXT_488);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_489);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_490);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_491);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicDiagnostic"));
    stringBuffer.append(TEXT_492);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_493);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.plugin.EcorePlugin"));
    stringBuffer.append(TEXT_494);
    stringBuffer.append(constraint);
    stringBuffer.append(TEXT_495);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_496);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_497);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_498);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_499);
    } else {
    stringBuffer.append(TEXT_500);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_501);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_502);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicDiagnostic"));
    stringBuffer.append(TEXT_503);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_504);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.plugin.EcorePlugin"));
    stringBuffer.append(TEXT_505);
    stringBuffer.append(constraint);
    stringBuffer.append(TEXT_506);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_507);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_508);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_509);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_510);
    }}}
    stringBuffer.append(TEXT_511);
    }
    }
    stringBuffer.append(TEXT_512);
    stringBuffer.append(genPackage.getValidatorClassName());
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_513);
    return stringBuffer.toString();
  }
}
