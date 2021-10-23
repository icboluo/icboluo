package icboluo.object;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 档案视图对象
 *
 * @author icboluo
 * @date 2020/12/3 21:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArchivesVO {

    /**
     * id
     */
    private String id;


    /**
     * 名称
     */
    private String name;

}
