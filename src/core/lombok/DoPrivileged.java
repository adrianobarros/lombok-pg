/*
 * Copyright © 2011 Philipp Eichhorn
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package lombok;

import java.lang.annotation.*;

/**
 * Before:
 * <pre>
 * &#64;DoPrivileged int test1() {
 *   // something
 *   return 0;
 * }
 *
 * &#64;DoPrivileged void test2() {
 *   // something else
 * }
 * </pre>
 * After:
 * <pre>
 * int test1() {
 *   return AccessController.doPrivileged(new PrivilegedAction&lt;Integer&gt;() {
 *     public Integer run() {
 *       // something
 *       return 0;
 *     }
 *   });
 * }
 *
 * void test2() {
 *   AccessController.doPrivileged(new PrivilegedAction&lt;Void&gt;() {
 *     public Void run() {
 *       // something else
 *       return null;
 *     }
 *   });
 * }
 * </pre>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface DoPrivileged {

	/**
	 * Method that should be used to sanitize the parameter.
	 * Any Exception thrown by this method might throw an exception.
	 */
	@Target(ElementType.PARAMETER)
	@Retention(RetentionPolicy.SOURCE)
	public static @interface SanitizeWith {
		String value();
	}
}