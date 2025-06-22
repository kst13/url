import fs from 'fs/promises';
import { Claude} from '@anthropic-ai/claude-code';

const claude = new Claude();

async function reviewDiff() {
    await fs.readFile('pr.diff', 'utf8');

    const res = await claude.message.create({
        model: 'claude-4-sonnet',
        max_tokens: 1000,
        messages: [
            {
                role: 'user',
                content: `경험 많은 시니어 개발자로서, 아래 git diff 내용을 보고 간결하게 코드 리뷰를 수행해주세요. 만약에 심각한 오류가 있다면 알려주세요.`
            }
        ]
    });

    const reviewText = res.content[0].text;
    await fs.writeFile('review.md', reviewText);
    console.log('✅ Claude review complete : created review.md')
}

reviewDiff().catch(console.error);
