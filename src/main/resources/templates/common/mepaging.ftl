<#macro paging pagingList url parameterMap={}>
    <#if pagingList??>
        <#if parameterMap??>
            <#local parameter = "" />
            <#list parameterMap?keys as key>
                <#if parameterMap[key]?? && parameterMap[key] != "" && key != "pageNum">
                    <#local parameter = parameter + "&" + key + "=" + parameterMap[key] />
                </#if>
            </#list>
        </#if>
        <#if parameter != "">
            <#local parameter = "&" + parameter/>
        </#if>

        <div class="row me-p-20">
            <div class="col-md-6">
                <div>
                    共&nbsp;<i>${pagingList.total}</i>&nbsp;条记录，第&nbsp;<i>${pagingList.current}/${(pagingList.total/pagingList.size)?ceiling}</i>&nbsp;页
                </div>
            </div>
            <div class="col-md-6">
                <nav aria-label="Page navigation">
                    <ul class="pagination justify-content-end">
                        <li class="page-item<#if pagingList.current==1> disabled</#if>"><a class="page-link"
                                                                                                  href="${url}?pageNum=1${parameter}">首页</a>
                        </li>
                        <li class="page-item<#if pagingList.current==1> disabled</#if>"><a class="page-link"
                                                                                                  href="${url}?pageNum=${pagingList.current - 1}">上一页</a>
                        </li>
                        <#--<#list pagingList.navigatepageNums as element>
                            <#if element==pagingList.pageNum>
                                <li class="page-item active"><a class="page-link"
                                                                href="${url}?pageNum=${element}${parameter}">${element}</a></li>
                            </#if>
                            <#if element!=pagingList.pageNum>
                                <li class="page-item"><a class="page-link"
                                                         href="${url}?pageNum=${element}${parameter}">${element}</a></li>
                            </#if>
                        </#list>-->
                        <li class="page-item<#if pagingList.current==(pagingList.total/pagingList.size)?ceiling> disabled</#if>"><a class="page-link"
                                                                                                 href="${url}?pageNum=${pagingList.current + 1}">下一页</a>
                        </li>
                        <li class="page-item<#if pagingList.current==(pagingList.total/pagingList.size)?ceiling> disabled</#if>"><a class="page-link"
                                                                                                 href="${url}?pageNum=${(pagingList.total/pagingList.size)?ceiling}">尾页</a>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>
    </#if>
</#macro>