const contentWrapper = {
    template:'<div class="content-wrapper">\n' +
    '        <!-- 内容头部 -->\n' +
    '        <section class="content-header">\n' +
    '            <h1>\n' +
    '                我的笔记\n' +
    '            </h1>\n' +
    '            <ol class="breadcrumb">\n' +
    '                <li><a href="#" @click="this.$parent.fun12_1"><i class="fa fa-dashboard"></i>添加标签</a></li>\n' +
    '                <li><a href="#" @click="this.$parent.fun8_1"><i class="fa fa-dashboard"></i>删除笔记</a></li>\n' +
    '            </ol>\n' +
    '        </section>\n' +
    '        <!-- 内容头部 /-->\n' +
    '\n' +
    '        <!-- 正文区域 -->\n' +
    '        <section class="content">\n' +
    '            <div class="row">\n' +
    '                <!--二级侧边导航-->\n' +
    '                <div class="col-md-3">\n' +
    '                    <div class="box box-primary">\n' +
    '                        <!-- sidebar: style can be found in sidebar.less -->\n' +
    '                        <section class="sidebar">\n' +
    '                            <!-- sidebar menu: : style can be found in sidebar.less -->\n' +
    '                            <ul class="sidebar-menu">\n' +
    '                                <!--新建笔记-->\n' +
    '                                <li id="add-notebook">\n' +
    '                                   <a @click="$parent.fun7" class="btn-default btn-flat">\n' +
    '                                       <i class="fa fa-circle-o"></i> 新建笔记\n' +
    '                                   </a>\n' +
    '                                </li>\n' +
    '                                <!--笔记一-->\n' +
    '                                <li v-for="(nb,index) in this.$parent.notebooks">\n' +
    '                                    <a href="#">\n' +
    '                                        <i class="fa fa-edit"></i> <span @click="funA_1(nb.nid)">{{nb.notebookName}}</span>\n' +
    '                                    </a>\n' +
    '                                </li>\n' +
    '\n' +
    '                            </ul>\n' +
    '                        </section>\n' +
    '                        <!-- /.sidebar -->\n' +
    '                    </div>\n' +
    '                </div>\n' +
    '\n' +
    '                <!--文本编辑器-->\n' +
    '                <div class="col-md-9">\n' +
    '                    <!--<div>-->\n' +
    '                    <div class="col-md-12">\n' +
    '                        <div class="box box-info">\n' +
    '                            <div class="box-header">\n' +
    '                                <h3 class="box-title">{{this.$parent.notebook.notebookName}}\n' +
    '                                    <small>Advanced and full of features</small>\n' +
    '                                </h3>\n' +
    '                                <!-- tools box -->\n' +
    '                                <div class="pull-right box-tools">\n' +
    '                                    <button type="button" class="btn btn-info btn-sm" data-widget="collapse" data-toggle="tooltip" title="Collapse">\n' +
    '                                        <i class="fa fa-minus"></i></button>\n' +
    '                                </div>\n' +
    '                                <!-- /. tools -->\n' +
    '                            </div>\n' +
    '                            <!-- /.box-header -->\n' +
    '                            <div class="box-body pad">\n' +
    '                                <form @submit.prevent="$parent.fun9">\n' +
    '                                    <textarea id="editor1" name="editor1-textarea" rows="10" cols="80">\n' +
    '                                    </textarea>\n' +
    '                                    <input type="submit" value="保存">\n' +
    '                                </form>\n' +
    '                            </div>\n' +
    '                        </div>\n' +
    '                        <!-- /.box -->\n' +
    '                    </div>\n' +
    '                    <!--</div>-->\n' +
    '                </div>\n' +
    '            </div>\n' +
    '        </section>\n' +
    '        <!-- 正文区域 /-->\n' +
    '    </div>',
    created(){
        this.$parent.notebook.categoryId = this.$route.query.cid;
    },

    methods:{
        funA_1:function (nid) {
            if(!CKEDITOR.instances.editor1){
                CKEDITOR.replace('editor1');
                this.$parent.editor1 = CKEDITOR.instances.editor1;
            }
            this.$parent.notebook.notebookId=nid;
            _this=this;
            axios.post('/editor/findNotebookByNid.do',
                {
                    notebookId:nid,
                })
                .then(function (response) {
                    _this.$parent.notebook.notebookName = response.data.notebookName;
                    _this.$parent.notebook.notebookContent = response.data.notebookContent;
                    _this.$parent.notebook.trashStatus = response.data.trashStatus;
                    //回显笔记内容
                    _this.$parent.editor1.setData(_this.$parent.notebook.notebookContent);
                })
                .catch(function (error) {
                    console.log(error);
                });
        }
    }
};