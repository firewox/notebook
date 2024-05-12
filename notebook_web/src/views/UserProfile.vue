<template>
  <v-container
    id="user-profile-view"
    fluid
    tag="section"
  >
    <v-row justify="center">
      <v-col
        cols="12"
        md="8"
      >
        <material-card
          color="primary"
          icon="mdi-account-outline"
        >
          <template #title>
            <small class="text-body-1">{{username}}</small>
          </template>

          <v-form>
            <v-container class="py-0">
              <v-row>
                <v-col
                cols="12"
                md="4"
                >
                  <v-text-field
                  color="purple"
                  label="加入时间"
                  readonly="true"
                  :value="created"
                  />
                </v-col>
                <v-col
                  cols="12"
                  md="4"
                >
                  <v-text-field
                    color="purple"
                    label="生日"
                    readonly="true"
                    :value="birthday"
                  />
                </v-col>

                <v-col cols="12">
                  <v-textarea
                    color="purple"
                    label="个人说明"
                    readonly="true"
                    value="问世间，情为何物？直教生死相许。天南地北双飞客，老翅几回寒暑。欢乐趣，离别苦，就中更有痴儿女。君应有语，渺万里层云，千山暮雪，只影向谁去？"
                  />
                </v-col>

                <v-col
                  cols="12"
                  class="text-right"
                >
                </v-col>
              </v-row>
            </v-container>
          </v-form>
        </material-card>
      </v-col>

      <v-col
        cols="12"
        md="4"
      >
        <app-card class="mt-4 text-center">
          <v-img
            class="rounded-circle elevation-6 mt-n12 d-inline-block"
            :src="require('@/assets/pic1.png')"
            width="128"
          />

          <v-card-text class="text-center">
            <h6 class="text-h6 mb-2 text--secondary">
              作者
            </h6>

            <h4 class="text-h4 mb-3 text--primary">
              {{username}}
            </h4>

            <p class="text--secondary">
              君不见黄河之水天上来，奔流到海不复回。<br>
              君不见高堂明镜悲白发，朝如青丝暮成雪。<br>
              人生得意须尽欢，莫使金樽空对月。<br>
              天生我材必有用，千金散尽还复来。<br>
              烹羊宰牛且为乐，会须一饮三百杯。<br>
              岑夫子，丹丘生，将进酒，杯莫停。<br>
              与君歌一曲，请君为我倾耳听。<br>
              钟鼓馔玉不足贵，但愿长醉不愿醒。<br>
              古来圣贤皆寂寞，惟有饮者留其名。<br>
              陈王昔时宴平乐，斗酒十千恣欢谑。<br>
              主人何为言少钱，径须沽取对君酌。<br>
              五花马、千金裘，<br>呼儿将出换美酒，与尔同销万古愁。<br>
            </p>

            <v-btn
              class="mr-0"
              color="primary"
              min-width="100"
              rounded
              @click="fun1"
            >
              返回
            </v-btn>
          </v-card-text>
        </app-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
  export default {
    name: 'LabelView',
    data: () => ({
      username: '李白',
      birthday: '2000-12-12',
      created: '2022-02-02',
    }),
    methods: {
      fun1: function () {
        this.$router.push({ path: '/' }).catch(e => { e = null })
      },
    },
    created () {
      this.username = this.$that.user.username
      this.birthday = this.$that.user.birthday
      this.created = this.$that.user.created
    },
  }
</script>
