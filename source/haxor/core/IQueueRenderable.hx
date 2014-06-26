/*
HAXOR HTML5 ENGINE (c) 2013 - 2014 by Eduardo Pons - eduardo@thelaborat.org

HAXOR HTML5 ENGINE is licensed under a
Creative Commons Attribution-NoDerivs 3.0 Unported License.

You should have received a copy of the license along with this
work.  If not, see <http://creativecommons.org/licenses/by-nd/3.0/>.
 */
package haxor.core;

/**
 * ...
 * @author Eduardo Pons
 */
interface IQueueRenderable
{

	/**
	 * 
	 * @return
	 */
	public function GetQueue():Int;
	
	/**
	 * 
	 */
	public function OnRender() :Void;
}